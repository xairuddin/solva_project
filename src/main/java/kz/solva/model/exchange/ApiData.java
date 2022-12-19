package kz.solva.model.exchange;

import lombok.Data;

@Data
public class ApiData {
    String symbol;
    String interval;
    String apikey;

    @Override
    public String toString() {
        return "ApiData{" +
                "symbol='" + symbol + '\'' +
                ", interval='" + interval + '\'' +
                ", apikey='" + apikey + '\'' +
                '}';
    }
}
