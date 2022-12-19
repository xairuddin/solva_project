package kz.solva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "category_name")
    String category_name;

    @OneToMany(mappedBy = "categoryOwner")
    @JsonIgnore
    private List<Limit> limits;

}

