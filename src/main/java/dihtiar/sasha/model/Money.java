package dihtiar.sasha.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class Money implements Serializable {
    @Column(name = "amount")
    private Double amountMoney;

    @ManyToOne
    @JoinColumn(name = "DICTIONARY_ID")
    private MyCurrency myCurrency;

    public Money(Double amountMoney, MyCurrency myCurrency) {
        this.amountMoney = amountMoney;
        this.myCurrency = myCurrency;
    }

    public Double getAmountMoney() {
        return amountMoney;
    }

    public MyCurrency getMyCurrency() {
        return myCurrency;
    }

    protected Money() {
    }
}
