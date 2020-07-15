package app.entity;

import app.DTO.NiveauDTO;
import app.DTO.TypePasseDTO;

import javax.persistence.*;

@Entity(name = "TypePasse")
@Table(name = "reftypepasse")
public class TypePasse {

    public TypePasse() {}

    public TypePasse(Integer id, String code) {
        this.idtypepasse = id;
        this.codetypepasse = code;
    }

    @Id
    @Column(name = "idtypepasse", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtypepasse;

    @Column(name = "codetypepasse", nullable = false)
    private String codetypepasse;

    public Integer getIdtypepasse() {
        return idtypepasse;
    }

    public TypePasseDTO toTypePasseDTO() {
        return new TypePasseDTO(this);
    }

    public void setIdtypepasse(Integer idtypepasse) {
        this.idtypepasse = idtypepasse;
    }

    public String getCodetypepasse() {
        return codetypepasse;
    }

    public void setCodetypepasse(String codetypepasse) {
        this.codetypepasse = codetypepasse;
    }
}
