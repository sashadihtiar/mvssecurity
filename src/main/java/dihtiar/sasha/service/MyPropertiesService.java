package dihtiar.sasha.service;

import dihtiar.sasha.model.MyProperties;

import java.util.List;

public interface MyPropertiesService {

    List<MyProperties> showProps();

    void addProps(MyProperties properties);

    MyProperties findProps(String name);
}
