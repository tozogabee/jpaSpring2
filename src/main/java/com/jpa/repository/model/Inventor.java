package com.jpa.repository.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by java on 2017.08.31..
 */
@Entity
@Table(name="Inventor")
public class Inventor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="INVENTOR_ID")
    private long id;

    private String name;

    @OneToMany(mappedBy = "inventor")
    private List<Function> function;

    public Inventor() {
    }

    public Inventor(String name, List<Function> function) {
        this.name = name;
        this.function = function;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Function> getFunction() {
        return function;
    }

    public void setFunction(List<Function> function) {
        this.function = function;
    }
}
