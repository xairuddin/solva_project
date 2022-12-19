package kz.solva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitDto {
    private double limit_sum;
    private Long account_from;
    private Long category_id;
}
