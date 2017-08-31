package com.jpa.repository.model;

import com.jpa.config.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by java on 2017.08.31..
 */
@Entity
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name="INVENTOR_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Inventor inventor;


    private String formula;

    @Convert(converter = LocalDateConverter.class)
    @Column(name="INVENTION_DATE")
    private LocalDate inventionDate;

    private boolean integrateable;

    public Function() {
    }

    public Function(Inventor inventor, String formula, LocalDate inventionDate, boolean integrateable) {
        this.inventor = inventor;
        this.formula = formula;
        this.inventionDate = inventionDate;
        this.integrateable = integrateable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inventor getInventor() {
        return inventor;
    }

    public void setInventor(Inventor inventor) {
        this.inventor = inventor;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public LocalDate getInventionDate() {
        return inventionDate;
    }

    public void setInventionDate(LocalDate inventionDate) {
        this.inventionDate = inventionDate;
    }

    public boolean isIntegrateable() {
        return integrateable;
    }

    public void setIntegrateable(boolean integrateable) {
        this.integrateable = integrateable;
    }
}
