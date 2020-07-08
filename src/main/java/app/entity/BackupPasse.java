package app.entity;

import app.DTO.NiveauDTO;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "BackupPasse")
@Table(name = "backuppasse")
public class BackupPasse {

    @Id
    @Column(name = "idbackup", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idbackup;

    @Column(name = "backup", nullable = false, columnDefinition="blob")
    private byte[] backup;

    @Column(name = "nbpasses", nullable = false)
    private Integer nbpasses;

    @Column(name = "backupsize", nullable = false)
    private Long backupsize;

    @Column(name = "horodatage", nullable = false)
    private Date horodatage;

    public Integer getNbpasses() {
        return nbpasses;
    }

    public void setNbpasses(Integer nbpasses) {
        this.nbpasses = nbpasses;
    }

    public Long getBackupsize() {
        return backupsize;
    }

    public void setBackupsize(Long backupsize) {
        this.backupsize = backupsize;
    }

    public Integer getIdbackup() {
        return idbackup;
    }

    public void setIdbackup(Integer idbackup) {
        this.idbackup = idbackup;
    }

    public byte[] getBackup() {
        return backup;
    }

    public void setBackup(byte[] backup) {
        this.backup = backup;
    }

    public Date getHorodatage() {
        return horodatage;
    }

    public void setHorodatage(Date horodatage) {
        this.horodatage = horodatage;
    }
}
