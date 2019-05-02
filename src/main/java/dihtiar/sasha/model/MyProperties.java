package dihtiar.sasha.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PROPERTIES")
public class MyProperties extends Dictionary {
    @Column(name = "PROP_KEY")
    private String namek;

    @Column(name = "NAME")
    private String value;

    public String getNamek() {
        return namek;
    }

    public void setNamek(String namek) {
        this.namek = namek;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
