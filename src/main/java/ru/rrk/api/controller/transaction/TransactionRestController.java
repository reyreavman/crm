package ru.rrk.api.controller.transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrk.api.dto.transaction.response.TransactionDTO;
import ru.rrk.core.service.transaction.TransactionService;

@RestController
@RequestMapping("/crm-api/v1/transactions/{transactionId:\\d+}")
@RequiredArgsConstructor
public class TransactionRestController {
    private final TransactionService transactionService;

    @Operation(summary = "Получение информации о транзакции")
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
                                                        "fieldName": "transactionId",
                                                        "message": "должно быть больше 0"
                                                    }
                                                ],
                                                "creationDateTime": "2024-10-19T22:42:04.0371481"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Транзакция не найдена",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping
    public TransactionDTO getTransactionInfo(@Positive @PathVariable long transactionId) {
        return transactionService.findTransactionById(transactionId);
    }
}
