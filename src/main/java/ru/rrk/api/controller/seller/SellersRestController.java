package ru.rrk.api.controller.seller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.filters.BestSellerFilters;
import ru.rrk.api.dto.filters.MinTransactionsFilters;
import ru.rrk.api.dto.seller.request.CreateSellerDTO;
import ru.rrk.api.dto.seller.response.SellerDTO;
import ru.rrk.core.service.analytics.AnalyticsService;
import ru.rrk.core.service.seller.SellerService;

@RestController
@RequestMapping("/crm-api/v1/sellers")
@RequiredArgsConstructor
public class SellersRestController {
    private final SellerService sellerService;
    private final AnalyticsService analyticsService;

    @Operation(summary = "Получение списка продавцов")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
    })
    @GetMapping
    public PagedData<SellerDTO> getPagedSellers(@ParameterObject @PageableDefault(value = 20) Pageable pageable,
                                                @ParameterObject MinTransactionsFilters minTransactionsFilters) {
        if (minTransactionsFilters.minTransactionsSum() != null)
            return analyticsService.findSellersWithTransactionSumLessThan(pageable, minTransactionsFilters);
        return sellerService.findPagedSellers(pageable);
    }

    @Operation(summary = "Создание нового продавца")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Продавец создан",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SellerDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Переданы невалидные параметры",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "violationsExceptions": [
                                                    {
                                                        "fieldName": "name",
                                                        "message": "Имя не должно быть пустым"
                                                    },
                                                    {
                                                        "fieldName": "contactInfo",
                                                        "message": "Контактные данные не должны быть пустыми"
                                                    }
                                                ],
                                                "creationDateTime": "2024-10-19T19:37:35.1767248"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SellerDTO createSeller(@Valid @RequestBody CreateSellerDTO createSellerDTO) {
        return sellerService.createSeller(createSellerDTO);
    }

    @Operation(summary = "Получение самого продуктивного продавца")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Переданы неверные фильтры",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("best")
    public SellerDTO getBestSeller(@ParameterObject BestSellerFilters bestSellerFilters) {
        return analyticsService.findBestSeller(bestSellerFilters);
    }
}

