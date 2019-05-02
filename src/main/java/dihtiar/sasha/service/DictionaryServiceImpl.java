package dihtiar.sasha.service;

import dihtiar.sasha.model.MyProperties;
import dihtiar.sasha.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    DictionaryRepository dictionaryRepository;

    @Override
    public void addProps(MyProperties properties) {
        dictionaryRepository.save(properties);
    }
}
