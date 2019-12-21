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
public class Userstory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String titre;

	@ManyToOne
	@JoinColumn(nullable=false)
	private Backlog backlog;

	@OneToMany(
			targetEntity= Commentaire.class,
			cascade=ALL,
			mappedBy="userstory",
			orphanRemoval=true,
			fetch = FetchType.EAGER)
	private List<Commentaire> commentaires = new ArrayList();
}
