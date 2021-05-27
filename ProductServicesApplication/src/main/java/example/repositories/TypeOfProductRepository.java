package example.repositories;

import example.entities.TypeOfProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfProductRepository extends JpaRepository<TypeOfProductEntity,Long> {

}