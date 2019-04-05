package dihtiar.sasha.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CURRENCY")
@JsonAutoDetect
public class My—urrency extends Dictionary {

    @Column(name = "NAME")
    private String ÒurrencyName;

    public String get—urrencyName() {
        return ÒurrencyName;
    }

    public void set—urrencyName(String ÒurrencyName) {
        this.ÒurrencyName = ÒurrencyName;
    }
}
