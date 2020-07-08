package app.entity;

import app.DTO.PasseDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Passe")
@Table(name = "passes")
public class Passe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "idniveau", // passe.idniveau
            referencedColumnName="idniveau", // refniveau.idniveau
            foreignKey=@ForeignKey(name = "Fk_passe_niveau"), // FK créée dans passe
            nullable=false,
            columnDefinition = "integer")
    private Niveau niveau;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "idtypepasse", // passe.idtypepasse
            referencedColumnName="idtypepasse", // reftypepasse.idtypepasse
            foreignKey=@ForeignKey(name = "Fk_passe_typepasse"), // FK créée dans passe
            nullable=false,
            columnDefinition = "integer")
    private TypePasse typepasse;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "cavalier", nullable = true, length = 500)
    private String cavalier;

    @Column(name = "cavaliere", nullable = true, length = 500)
    private String cavaliere;

    @Column(name = "video", nullable = true, length = 500)
    private String video;

    public TypePasse getTypepasse() {
        return typepasse;
    }

    public void setTypepasse(TypePasse typepasse) {
        this.typepasse = typepasse;
    }

    public PasseDTO toPasseDTO() {
        return new PasseDTO(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCavalier() {
        return cavalier;
    }

    public void setCavalier(String cavalier) {
        this.cavalier = cavalier;
    }

    public String getCavaliere() {
        return cavaliere;
    }

    public void setCavaliere(String cavaliere) {
        this.cavaliere = cavaliere;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
