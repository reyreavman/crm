package ru.rrk.api.controller.transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.transaction.request.CreateTransactionDTO;
import ru.rrk.api.dto.transaction.response.TransactionDTO;
import ru.rrk.core.service.transaction.TransactionService;

@RestController
@RequestMapping("/crm-api/v1/transactions")
@RequiredArgsConstructor
@Validated
public class TransactionsRestController {
    private final TransactionService transactionService;

    @Operation(summary = "Получение списка транзакций")
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
                                                "violationsExceptions": [
                                                    {
                                                        "fieldName": "sellerId",
                                                        "message": "должно быть больше 0"
                                                    }
                                                ],
                                                "creationDateTime": "2024-10-19T22:42:04.0371481"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public PagedData<TransactionDTO> getPagedTransactions(@ParameterObject @PageableDefault(value = 20) Pageable pageable,
                                                          @Positive @RequestParam(required = false) Long sellerId) {
        return transactionService.findPagedTransactions(sellerId, pageable);
    }

    @Operation(summary = "Создание новой транзакции")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Транзакция создана",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransactionDTO.class)
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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь с id = * не найден",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "message": "Пользователь с id = 111 не найден",
                                                "creationDateTime": "2024-10-19T23:03:59.009616"
                                            }
                                            """
                            )
                    )
            ),
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransactionDTO createTransaction(@Valid @RequestBody CreateTransactionDTO createTransactionDTO) {
        return transactionService.createTransaction(createTransactionDTO);
    }
}
