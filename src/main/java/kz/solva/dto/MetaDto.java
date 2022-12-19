package kz.solva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDto {
    String symbol;
    String interval;
    String currency_base;
    String currency_quote;
    String type;
    Date datetime;
    double close;
}
