package dihtiar.sasha.repository;

import dihtiar.sasha.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    void deleteHallByName(String name);

    Hall findHallByName(String name);
}
