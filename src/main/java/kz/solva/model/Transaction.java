package kz.solva.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "transaction")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_from")
    private Long account_from;
    @Column(name = "account_to")
    private Long account_to;
    @Column(name = "currency_shortname")
    private String currency_shortname;
    @Column(name = "sum")
    private double sum;
    @Column(name = "expense_category")
    private String expense_category;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "limit_exceeded")
    private boolean limit_exceeded;

    @ManyToOne
    @JoinColumn(name = "limit_id", referencedColumnName = "id")
    private Limit limitOwner;

}
