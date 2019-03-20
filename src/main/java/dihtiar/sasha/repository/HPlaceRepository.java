package dihtiar.sasha.repository;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HPlaceRepository extends JpaRepository<HPlace, Long> {
    HPlace findHPlaceByHall_NameAndRAndP(String hall_name, Long r, Long p);

    void deleteByHall_Id(Long hall_id);

    List<HPlace> getHPlaceByHall(Hall hall);
}
