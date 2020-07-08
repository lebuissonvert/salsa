package app.DTO;

import app.entity.Passe;
import app.entity.Stat;

import java.io.Serializable;
import java.util.Date;

public class StatDTO implements Serializable {
    private Integer id;
    private String typeaction;
    private PasseDTO passeDTO;
    private Date horodatage;

    private StatDTO(){}

    public StatDTO(String p_typeaction, Integer p_idPasse) {
        this.typeaction = p_typeaction;
        this.passeDTO = new PasseDTO();
        this.passeDTO.setId(p_idPasse);
    }

    public StatDTO(Stat p_stat) {
        this.id = p_stat.getId();
        this.typeaction = p_stat.getTypeaction();
        this.passeDTO = p_stat.getPasse().toPasseDTO();
        this.horodatage = p_stat.getHorodatage();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeaction() {
        return typeaction;
    }

    public void setTypeaction(String typeaction) {
        this.typeaction = typeaction;
    }

    public PasseDTO getPasseDTO() {
        return passeDTO;
    }

    public void setPasseDTO(PasseDTO passeDTO) {
        this.passeDTO = passeDTO;
    }

    public Date getHorodatage() {
        return horodatage;
    }

    public void setHorodatage(Date horodatage) {
        this.horodatage = horodatage;
    }
}
