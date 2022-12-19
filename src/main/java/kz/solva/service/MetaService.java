package kz.solva.service;

import kz.solva.dto.MetaDto;
import kz.solva.mapper.MetaMapper;
import kz.solva.model.exchange.Meta;
import kz.solva.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaService {

    public final MetaRepository metaRepository;
    public final MetaMapper metaMapper;

    @Autowired
    public MetaService(MetaRepository metaRepository, MetaMapper metaMapper) {
        this.metaRepository = metaRepository;
        this.metaMapper = metaMapper;
    }

    public MetaDto saveMetaDto (MetaDto metaDto) {
        Meta response = metaRepository.save(metaMapper.apiToEntity(metaDto));
        return metaMapper.entityToApi(response);
    }
    public Meta getMeta (String symbol) {
        List<Meta> metas = metaRepository.findExchangesBySymbol(symbol);
        return metas.get(0);
    }
}
