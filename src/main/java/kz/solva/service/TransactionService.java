package kz.solva.service;

import kz.solva.dto.LimitDto;
import kz.solva.dto.TransactionDto;
import kz.solva.mapper.TransactionMapper;
import kz.solva.model.Category;
import kz.solva.model.Limit;
import kz.solva.model.Transaction;
import kz.solva.repository.CategoryRepository;
import kz.solva.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final LimitService limitService;
    private final CategoryRepository categoryRepository;
    private final ExchangeService exchangeService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, LimitService limitService, CategoryRepository categoryRepository, ExchangeService exchangeService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.limitService = limitService;
        this.categoryRepository = categoryRepository;
        this.exchangeService = exchangeService;
    }

    // save transaction
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.apiToEntity(transactionDto);
        Limit limit = new Limit();
        List<Limit> limits = limitService.findLimitByAccount_fromAndExpense_category(transaction.getAccount_from(), transaction.getExpense_category());
        if (limits.size() != 0){
            limit = limits.get(0);
        }else {
            limit = null;
        }

        System.out.println("Have limit: " + limit);

        if (limit == null){
            Category category = categoryRepository.findByCategory_name(transaction.getExpense_category()).orElseThrow();
            LimitDto limitDto = new LimitDto();
            limitDto.setAccount_from(transaction.getAccount_from());
            limitDto.setLimit_sum(0);
            limitDto.setCategory_id(category.getId());
            limit = limitService.newLimit(limitDto);
        }

        double sum = exchangeService.exchangeToUsd(transaction.getCurrency_shortname(), transaction.getSum());


        double limit_rest = limit.getLimit_rest() - sum;
        if (limit_rest >= 0) {
            transaction.setLimit_exceeded(false);
        }else {
            transaction.setLimit_exceeded(true);
        }

        limit.setLimit_rest(limit_rest);
        limitService.save(limit);
        transaction.setLimitOwner(limit);

        return transactionMapper.entityToApi(transactionRepository.save(transaction));
    }

    // save list of transaction

    public List<TransactionDto> saveTransactions (List<TransactionDto> transactionDtos) {

        List<Transaction> transactionList = transactionDtos.stream().map(transactionMapper::apiToEntity).collect(Collectors.toList());
        transactionList = transactionRepository.saveAll(transactionList);

        List<TransactionDto> response = transactionList.stream().map(transactionMapper::entityToApi).collect(Collectors.toList());

        return response;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
    public List<Transaction> getAllLimitedTransactions() {
        return transactionRepository.findAllLimitedTransactions();
    }
    public List<TransactionDto> getAllTransactionDtos(){
        return transactionMapper.TransactionListToTransactionDtoList(transactionRepository.findAll());
    }
    public List<TransactionDto> getAllLimitedTransactionDtos() {
        return transactionMapper.TransactionListToTransactionDtoList(transactionRepository.findAllLimitedTransactions());
    }
    public List<Transaction> getAllTransactionsByAccount(Long account) {
        return transactionRepository.findAllTransactionsByAccount(account);
    }
    public List<Transaction> getAllLimitedTransactionsByAccount(Long account) {
        return transactionRepository.findAllLimitedTransactionsByAccount(account);
    }


}

