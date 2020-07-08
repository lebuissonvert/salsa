package app.entity;

import app.DTO.PasseDTO;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Stat")
@Table(name = "stats")
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "typeaction", nullable = false)
    private String typeaction;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "idpasse", // stats.idpasse
            referencedColumnName="id", // passes.id
            foreignKey=@ForeignKey(name = "Fk_passe_stat"), // FK créée dans stats
            nullable=false,
            columnDefinition = "integer")
    private Passe passe;

    @Column(name = "horodatage")
    private Date horodatage;

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

    public Passe getPasse() {
        return passe;
    }

    public void setPasse(Passe passe) {
        this.passe = passe;
    }

    public Date getHorodatage() {
        return horodatage;
    }

    public void setHorodatage(Date horodatage) {
        this.horodatage = horodatage;
    }
}
