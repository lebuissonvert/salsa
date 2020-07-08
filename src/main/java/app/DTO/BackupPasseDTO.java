package app.DTO;

import app.entity.BackupPasse;
import app.entity.Niveau;

import java.io.Serializable;
import java.util.Date;

public class BackupPasseDTO implements Serializable {
    private Integer idbackup;
    private byte[] backup;
    private Integer nbpasses;
    private Long backupsize;
    private Date horodatage;

    private BackupPasseDTO(){}

    public BackupPasseDTO(Integer p_idbackup, byte[] p_backup, Integer p_nbpasses, Long p_backupsize, Date p_horodatage) {
        this.idbackup = p_idbackup;
        this.backup = p_backup;
        this.nbpasses = p_nbpasses;
        this.backupsize = p_backupsize;
        this.horodatage = p_horodatage;
    }

    public BackupPasseDTO(BackupPasse p_backuppasse) {
        this.idbackup = p_backuppasse.getIdbackup();
        this.backup = p_backuppasse.getBackup();
        this.nbpasses = p_backuppasse.getNbpasses();
        this.backupsize = p_backuppasse.getBackupsize();
        this.horodatage = p_backuppasse.getHorodatage();
    }

    public Integer getNbpasses() {
        return nbpasses;
    }

    public void setNbpasses(Integer nbpasses) {
        this.nbpasses = nbpasses;
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

    public Long getBackupsize() {
        return backupsize;
    }

    public void setBackupsize(Long backupsize) {
        this.backupsize = backupsize;
    }
}
