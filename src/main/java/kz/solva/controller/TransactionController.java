package kz.solva.controller;

import io.swagger.annotations.Api;
import kz.solva.dto.TransactionDto;
import kz.solva.model.Transaction;
import kz.solva.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Api(tags = "transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //get transactions
    @GetMapping("/getAllDtos")
    public List<TransactionDto> getAllTransactionDtos(){
        return transactionService.getAllTransactionDtos();
    }
    @GetMapping("/getAllLimitedDtos")
    public List<TransactionDto> getAllLimitedTransactionDtos(){
        return transactionService.getAllLimitedTransactionDtos();
    }
    // get dto transactions
    @GetMapping("/getAll")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("/getAllLimited")
    public List<Transaction> getAllLimitedTransactions(){
        return transactionService.getAllLimitedTransactions();
    }

    // get transactions by account
    @GetMapping("/getAll/{account_from}")
    public List<Transaction> getAllTransactionsByAccount(@PathVariable Long account_from){
        return transactionService.getAllTransactionsByAccount(account_from);
    }
    // get transactions by account
    @GetMapping("/getAllLimited/{account_from}")
    public List<Transaction> getAllLimitedTransactionsByAccount(@PathVariable Long account_from){
        return transactionService.getAllLimitedTransactionsByAccount(account_from);
    }
    // save transaction
    @PostMapping("/post")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto) {
        System.out.println("Controller");
        System.out.println(transactionDto);
        return ResponseEntity.ok(transactionService.saveTransaction(transactionDto));
    }

}
