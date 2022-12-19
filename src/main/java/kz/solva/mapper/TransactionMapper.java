package kz.solva.mapper;

import kz.solva.dto.TransactionDto;
import kz.solva.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mappings({})
    List<TransactionDto> TransactionListToTransactionDtoList(List<Transaction> transactions);
    @Mappings({})
    TransactionDto entityToApi(Transaction Activity);
    @Mappings({})
    Transaction apiToEntity(TransactionDto dto);
}
