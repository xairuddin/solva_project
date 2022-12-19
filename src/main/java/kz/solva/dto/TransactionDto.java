package kz.solva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    Long account_from;
    Long account_to;
    String currency_shortname;
    double sum;
    String expense_category;
    Date date;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "account_from=" + account_from +
                ", account_to=" + account_to +
                ", currency_shortname='" + currency_shortname + '\'' +
                ", sum=" + sum +
                ", expense_category='" + expense_category + '\'' +
                ", date=" + date +
                '}';
    }
}
