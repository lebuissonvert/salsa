package app.DTO;

import app.entity.Passe;
import app.entity.TypePasse;

import java.io.Serializable;

public class PasseDTO implements Serializable {
    private Integer id;
    private NiveauDTO niveau;
    private TypePasseDTO typepasse;
    private String nom;
    private String cavalier;
    private String cavaliere;
    private String video;

    public PasseDTO(){}

    public PasseDTO(Passe p_passe) {
        this.id = p_passe.getId();
        this.niveau = p_passe.getNiveau().toNiveauDTO();
        this.typepasse = p_passe.getTypepasse().toTypePasseDTO();
        this.nom = p_passe.getNom();
        this.cavalier = p_passe.getCavalier();
        this.cavaliere = p_passe.getCavaliere();
        this.video = p_passe.getVideo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NiveauDTO getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauDTO niveau) {
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

    public TypePasseDTO getTypepasse() {
        return typepasse;
    }

    public void setTypepasse(TypePasseDTO typepasse) {
        this.typepasse = typepasse;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
