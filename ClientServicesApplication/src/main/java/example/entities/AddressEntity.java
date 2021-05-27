package example.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "address")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private ClientEntity clientEntity;

}
