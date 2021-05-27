package example.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "typeofproduct")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TypeOfProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "typeOfProductEntity")
    private Set<ProductEntity> productEntityList;
}
