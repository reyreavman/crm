package ru.rrk.api.controller.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrk.core.service.transaction.TransactionService;

@RestController
@RequestMapping("/crm-api/v1/transactions")
@RequiredArgsConstructor
public class TransactionsRestController {
    private final TransactionService transactionService;

    //TODO: Доделай контроллеры
}
