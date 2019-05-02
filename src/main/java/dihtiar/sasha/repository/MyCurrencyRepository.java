package dihtiar.sasha.repository;

import dihtiar.sasha.model.MyCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCurrencyRepository extends JpaRepository<MyCurrency, Long> {
    MyCurrency getMyCurrencyById(Long id);
}