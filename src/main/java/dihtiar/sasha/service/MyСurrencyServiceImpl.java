package dihtiar.sasha.service;

import dihtiar.sasha.model.MyCurrency;
import dihtiar.sasha.repository.MyCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class My—urrencyServiceImpl implements MyCurrencyService {

    @Autowired
    MyCurrencyRepository myCurrencyRepository;

    @Override
    public List<MyCurrency> getAll() {
        return myCurrencyRepository.findAll();
    }

    @Override
    public MyCurrency findByID(Long id) {
        return myCurrencyRepository.getMyCurrencyById(id);
    }
}