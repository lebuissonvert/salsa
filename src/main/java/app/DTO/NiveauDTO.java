package app.DTO;

import app.entity.Niveau;

import java.io.Serializable;

public class NiveauDTO implements Serializable {
    private Integer idniveau;
    private String codeniveau;

    private NiveauDTO(){}

    public NiveauDTO(Niveau p_niveau) {
        this.idniveau = p_niveau.getIdniveau();
        this.codeniveau = p_niveau.getCodeniveau();
    }

    public Integer getIdniveau() {
        return idniveau;
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
