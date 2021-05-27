package example.entities;

import example.repositories.StatusRepository;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "orders")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "status")
    private StatusRepository status;
    @Column(name = "price")
    private int price;
    @Column(name = "data")
    private Timestamp data;
    @OneToMany(mappedBy = "orderEntity" , cascade = CascadeType.ALL)
    private List<DetailedEntity> detailedEntityList;
    @Column(name = "clientid")
    private Long clientId;

}
