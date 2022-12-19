package kz.solva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "_limit")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double limit_sum;
    @Temporal(TemporalType.TIMESTAMP)
    private Date limit_datetime;
    private String limit_currency_shortname;
    private double limit_rest;
    private Long account_from;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryOwner;

    @OneToMany(mappedBy = "limitOwner")
    @JsonIgnore
    private List<Transaction> transactions;

}
