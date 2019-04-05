package dihtiar.sasha.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CURRENCY")
@JsonAutoDetect
public class My�urrency extends Dictionary {

    @Column(name = "NAME")
    private String �urrencyName;

    public String get�urrencyName() {
        return �urrencyName;
    }

    public void set�urrencyName(String �urrencyName) {
        this.�urrencyName = �urrencyName;
    }
}
