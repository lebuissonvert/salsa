package app.DTO;

import app.entity.Passe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaginatedPassesDTO implements Serializable {

    private Long totalRecords = 0L;
    private List<PasseDTO> passes = new ArrayList<>();

    public PaginatedPassesDTO(List<Passe> p_passes, Long p_totalRecords) {
        if(p_passes == null) {
            new PaginatedPassesDTO();
        } else {
            for(Passe curPasse : p_passes) {
                passes.add(curPasse.toPasseDTO());
            }
            this.totalRecords = p_totalRecords;
        }
    }

    public PaginatedPassesDTO() {
        passes = new ArrayList<>();
        this.totalRecords = 0L;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<PasseDTO> getPasses() {
        return passes;
    }

    public void setPasses(List<PasseDTO> passes) {
        this.passes = passes;
    }
}
