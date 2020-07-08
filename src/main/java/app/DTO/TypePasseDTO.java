package app.DTO;

import app.entity.Niveau;
import app.entity.TypePasse;

import java.io.Serializable;

public class TypePasseDTO implements Serializable {
    private Integer idtypepasse;
    private String codetypepasse;

    private TypePasseDTO(){}

    public TypePasseDTO(TypePasse p_typepasse) {
        this.idtypepasse = p_typepasse.getIdtypepasse();
        this.codetypepasse = p_typepasse.getCodetypepasse();
    }

    public Integer getIdtypepasse() {
        return idtypepasse;
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
