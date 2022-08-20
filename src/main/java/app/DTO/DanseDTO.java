package app.DTO;

import app.entity.Danse;

import java.io.Serializable;

public class DanseDTO implements Serializable {
    private Integer iddanse;
    private String libelledanse;
    private String codedanse;

    private DanseDTO(){}

    public DanseDTO(Danse p_danse) {
        this.iddanse = p_danse.getIddanse();
        this.libelledanse = p_danse.getLibelledanse();
        this.codedanse = p_danse.getCodedanse();
    }

    public Integer getIddanse() {
        return iddanse;
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
