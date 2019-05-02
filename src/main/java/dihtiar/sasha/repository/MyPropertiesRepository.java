package dihtiar.sasha.repository;

import dihtiar.sasha.model.MyProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPropertiesRepository extends JpaRepository<MyProperties, Long> {
    MyProperties findByNamek(String name);
}
