package kz.solva.mapper;

import kz.solva.dto.MetaDto;
import kz.solva.model.exchange.Meta;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetaMapper {
    @Mappings({})
    List<MetaDto> MetaListToMetaDtoList(List<Meta> metas);
    @Mappings({})
    MetaDto entityToApi(Meta meta);
    @Mappings({})
    Meta apiToEntity(MetaDto dto);
}
