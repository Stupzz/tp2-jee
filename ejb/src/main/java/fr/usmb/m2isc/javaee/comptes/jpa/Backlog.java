package fr.usmb.m2isc.javaee.comptes.jpa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
public class Backlog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int estimation;
    private int priorite;
    private String description;

    @OneToMany(
            targetEntity = Userstory.class,
            cascade = ALL,
            mappedBy = "backlog",
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Userstory> userstories = new ArrayList();
}
