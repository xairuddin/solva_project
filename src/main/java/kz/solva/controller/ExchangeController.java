package kz.solva.controller;

import io.swagger.annotations.Api;
import kz.solva.dto.MetaDto;
import kz.solva.model.exchange.ApiData;
import kz.solva.model.exchange.Meta;
import kz.solva.service.ExchangeService;
import kz.solva.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;
    private final MetaService metaService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService, MetaService metaService) {
        this.exchangeService = exchangeService;
        this.metaService = metaService;
    }

    @PostMapping("/saveExchange")
    public MetaDto save(@RequestBody ApiData apiData) {
        System.out.println(apiData);
        return exchangeService.saveExchange(apiData);
    }

    @GetMapping("/sss")
    public Meta get() {
        return metaService.getMeta("USD/KZT");
    }
}
