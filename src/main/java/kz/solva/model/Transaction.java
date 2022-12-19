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
    private Long id;
    private Long account_from;
    private Long account_to;
    private String currency_shortname;
    private double sum;
    private String expense_category;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private boolean limit_exceeded;

    @ManyToOne
    @JoinColumn(name = "limit_id", referencedColumnName = "id")
    private Limit limitOwner;

}
