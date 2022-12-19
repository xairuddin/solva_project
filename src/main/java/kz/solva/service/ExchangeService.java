package kz.solva.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.solva.dto.MetaDto;
import kz.solva.model.exchange.ApiData;
import kz.solva.model.exchange.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExchangeService {
    //my Api
    //6517f1e97d65484c87af200b02f1147b

    private final RestTemplate restTemplate;
    private final MetaService metaService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ExchangeService(RestTemplateBuilder restTemplateBuilder, MetaService metaService) {
        this.restTemplate = restTemplateBuilder.build();
        this.metaService = metaService;
    }

    public MetaDto saveExchange(ApiData apiData) {
        System.out.println(apiData);
        String url = "https://api.twelvedata.com/time_series?symbol="+apiData.getSymbol()+"&interval="+apiData.getInterval()+"&apikey=" +apiData.getApikey();
        String response = this.restTemplate.getForObject(url, String.class);
        JsonNode jsonData = null;
        try {
            jsonData = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MetaDto metaDto = new MetaDto();
        metaDto.setSymbol(jsonData.get("meta").get("symbol").asText());
        metaDto.setInterval(jsonData.get("meta").get("interval").asText());
        metaDto.setCurrency_base(jsonData.get("meta").get("currency_base").asText());
        metaDto.setCurrency_quote(jsonData.get("meta").get("currency_quote").asText());
        metaDto.setType(jsonData.get("meta").get("type").asText());

        String time = jsonData.get("values").get(0).get("datetime").asText();
        try {
            Date datetime = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            metaDto.setDatetime(datetime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        metaDto.setClose(jsonData.get("values").get(0).get("close").asDouble());

        return metaService.saveMetaDto(metaDto);
    }

    public double exchangeToUsd(String currency_type, double sum) {
        String symbol = "USD/" + currency_type;
        Meta meta = metaService.getMeta(symbol);
        return sum/meta.getClose();
    }
}
