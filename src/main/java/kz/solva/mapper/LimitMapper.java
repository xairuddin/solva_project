package kz.solva.mapper;

import kz.solva.dto.LimitDto;
import kz.solva.model.Limit;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LimitMapper {
    @Mappings({})
    List<LimitDto> LimitListToLimitDtoList(List<Limit> limits);
    @Mappings({})
    LimitDto entityToApi(Limit Limit);
    @Mappings({})
    Limit apiToEntity(LimitDto dto);
}
