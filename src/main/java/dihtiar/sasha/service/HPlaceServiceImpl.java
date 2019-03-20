package dihtiar.sasha.service;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Hall;
import dihtiar.sasha.repository.HPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HPlaceServiceImpl implements HPlaceService {

    @Autowired
    HPlaceRepository hPlaceRepository;

    @Transactional
    @Override
    public void addHPlace(HPlace hPlace) {
        Hall hall = hPlace.getHall();
        int i = Math.toIntExact(hall.getRows());
        int j = Math.toIntExact(hall.getPlaces());
        for (int r = 1; r <= i; r++) {
            for (int p = 1; p <= j; p++) {
                hPlace = new HPlace();
                hPlace.setHall(hall);
                hPlace.setR((long) r);
                hPlace.setP((long) p);
                hPlaceRepository.save(hPlace);
            }
        }
    }

    @Override
    public HPlace findHPlaceForTicket(String hall_name, Long r, Long p) {
        return hPlaceRepository.findHPlaceByHall_NameAndRAndP(hall_name, r, p);
    }

    @Transactional
    @Override
    public void deleteByHallID(Long id) {
        hPlaceRepository.deleteByHall_Id(id);
    }

    @Override
    public List<HPlace> findHPByHall(Hall hall) {
        return hPlaceRepository.getHPlaceByHall(hall);
    }
}
