package kz.solva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String category_name;

    @OneToMany(mappedBy = "categoryOwner")
    @JsonIgnore
    private List<Limit> limits;

}

