package dihtiar.sasha.service;

import dihtiar.sasha.model.MyCurrency;

import java.util.List;

public interface MyCurrencyService {

    List<MyCurrency> getAll();

    MyCurrency findByID(Long id);
}
