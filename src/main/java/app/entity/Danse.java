package app.entity;

import app.DTO.DanseDTO;
import app.DTO.TypePasseDTO;

import javax.persistence.*;

@Entity(name = "Danse")
@Table(name = "refdanse")
public class Danse {

    @Id
    @Column(name = "iddanse", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddanse;

    @Column(name = "libelledanse", nullable = false)
    private String libelledanse;

    @Column(name = "codedanse", nullable = false)
    private String codedanse;

    public Danse() {}

    public Danse(Integer id, String libelle, String code) {
        this.iddanse = id;
        this.libelledanse = libelle;
        this.codedanse = code;
    }

    public Integer getIddanse() {
        return iddanse;
    }

    public DanseDTO toDanseDTO() {
        return new DanseDTO(this);
    }

    public void setIddanse(Integer iddanse) {
        this.iddanse = iddanse;
    }

    public String getLibelledanse() {
        return libelledanse;
    }

    public void setLibelledanse(String libelledanse) {
        this.libelledanse = libelledanse;
    }

    public String getCodedanse() {
        return codedanse;
    }

    public void setCodedanse(String codedanse) {
        this.codedanse = codedanse;
    }
}
