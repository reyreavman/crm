package ru.rrk.api.controller.seller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrk.api.dto.seller.request.UpdateSellerDTO;
import ru.rrk.api.dto.seller.response.SellerDTO;
import ru.rrk.core.service.seller.SellerService;

@RestController
@RequestMapping("/crm-api/v1/sellers/{sellerId:\\d+}")
@RequiredArgsConstructor
public class SellerRestController {
    private final SellerService sellerService;

    @Operation(summary = "Получение данных о продавце")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Продавец не найден",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "message": "User with id = 10 not found",
                                                "creationDateTime": "2024-10-19T19:51:10.9290662"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public SellerDTO getSellerInfo(@Positive @PathVariable long sellerId) {
        return sellerService.findSellerById(sellerId);
    }

    @Operation(summary = "Обновление данных о продавца")
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
                    description = "Переданы невалидные параметры",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "violations": [
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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Продавец не найден",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                                    {
                                                      "message": "User with id = 10 not found",
                                                      "creationDateTime": "2024-10-19T19:51:10.9290662"
                                                    }
                                            """
                            )
                    )
            )
    })
    @PatchMapping
    public SellerDTO updateSeller(@Positive @PathVariable long sellerId,
                                  @Valid @RequestBody UpdateSellerDTO updateSellerDTO) {
        return sellerService.updateSeller(sellerId, updateSellerDTO);
    }

    @Operation(summary = "Удаление продавца")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос"
            )
    })
    @DeleteMapping
    public void deleteSeller(@Positive @PathVariable long sellerId) {
        sellerService.removeSeller(sellerId);
    }
}
