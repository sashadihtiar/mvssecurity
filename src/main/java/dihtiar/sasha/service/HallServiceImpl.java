package dihtiar.sasha.service;

import dihtiar.sasha.model.Hall;
import dihtiar.sasha.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class HallServiceImpl implements HallService {

    @Autowired
    HallRepository hallRepository;

    @Override
    public List<Hall> getAll() {
        return hallRepository.findAll();
    }

    @Transactional
    @Override
    public void addnewHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Transactional
    @Override
    public void deleteHall(String name) {
        hallRepository.deleteHallByName(name);
    }

    @Override
    public Hall findHallByName(String name) {
        return hallRepository.findHallByName(name);
    }
}
