package example.repositories;

import example.entities.DetailedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailedRepository extends JpaRepository<DetailedEntity,Long> {
}
