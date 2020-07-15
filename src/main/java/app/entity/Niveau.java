package app.entity;

import app.DTO.NiveauDTO;

import javax.persistence.*;

@Entity(name = "Niveau")
@Table(name = "refniveau")
public class Niveau {

    @Id
    @Column(name = "idniveau", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idniveau;

    @Column(name = "codeniveau", nullable = false)
    private String codeniveau;

    public Niveau(){}

    public Niveau(Integer p_idNiveau, String p_codeNiveau) {
        this.idniveau = p_idNiveau;
        this.codeniveau = p_codeNiveau;
    }

    public Integer getIdniveau() {
        return idniveau;
    }

    public NiveauDTO toNiveauDTO() {
        return new NiveauDTO(this);
    }

    public void setIdniveau(Integer idniveau) {
        this.idniveau = idniveau;
    }

    public String getCodeniveau() {
        return codeniveau;
    }

    public void setCodeniveau(String codeniveau) {
        this.codeniveau = codeniveau;
    }
}
