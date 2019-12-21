package fr.usmb.m2isc.javaee.comptes.jpa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Commentaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String auteur;
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Userstory userstory;
}
