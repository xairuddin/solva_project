package kz.solva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "_limit")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "limit_sum")
    private double limit_sum;
    @Column(name = "limit_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limit_datetime;
    @Column(name = "limit_currency_shortname")
    private String limit_currency_shortname;
    @Column(name = "limit_rest")
    private double limit_rest;
    @Column(name = "account_from")
    private Long account_from;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryOwner;

    @OneToMany(mappedBy = "limitOwner")
    @JsonIgnore
    private List<Transaction> transactions;
}
