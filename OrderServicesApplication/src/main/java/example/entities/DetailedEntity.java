package example.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "detailed")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DetailedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "orderid")
    private OrderEntity orderEntity;
    @Column(name = "productid")
    private Long productId;

}