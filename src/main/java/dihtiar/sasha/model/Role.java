package dihtiar.sasha.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ROLE")
@JsonAutoDetect
public class Role extends Dictionary {

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}