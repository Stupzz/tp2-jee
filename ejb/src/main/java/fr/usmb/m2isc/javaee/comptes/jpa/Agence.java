package fr.usmb.m2isc.javaee.comptes.jpa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "all", query = "SELECT a FROM Agence a"),
})
@Data
@Entity
public class Agence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String nom;

    @OneToOne
    private Backlog backlog;
}
