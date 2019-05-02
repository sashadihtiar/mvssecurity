package dihtiar.sasha.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CURRENCY")
@JsonAutoDetect
public class MyCurrency extends Dictionary {

    @Column(name = "NAME")
    private String currencyName;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

}