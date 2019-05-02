package dihtiar.sasha.service;

import dihtiar.sasha.model.MyProperties;
import dihtiar.sasha.repository.MyPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MyPropertiesServiceImpl implements MyPropertiesService {

    @Autowired
    MyPropertiesRepository myPropertiesRepository;

    @Override
    public List<MyProperties> showProps() {
        return myPropertiesRepository.findAll();
    }

    @Transactional
    @Override
    public void addProps(MyProperties properties) {
        myPropertiesRepository.save(properties);
    }

    @Override
    public MyProperties findProps(String name) {
        return myPropertiesRepository.findByNamek(name);
    }
}
