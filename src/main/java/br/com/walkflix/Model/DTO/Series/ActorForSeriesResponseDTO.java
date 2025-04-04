package br.com.walkflix.Model.DTO.Series;

import br.com.walkflix.Model.Enum.ActorStatus.ActorStatus;

import java.time.LocalDate;

public class ActorForSeriesResponseDTO {

    private int id;

    private String txActorName;

    private String txActorSurname;

    private ActorStatus status;

    private LocalDate dtBirthday;

    private String txCity;

    private String txProfilePicture;

    private String txBiography;

    public ActorForSeriesResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxActorName() {
        return txActorName;
    }

    public void setTxActorName(String txActorName) {
        this.txActorName = txActorName;
    }

    public String getTxActorSurname() {
        return txActorSurname;
    }

    public void setTxActorSurname(String txActorSurname) {
        this.txActorSurname = txActorSurname;
    }

    public ActorStatus getStatus() {
        return status;
    }

    public void setStatus(ActorStatus status) {
        this.status = status;
    }

    public LocalDate getDtBirthday() {
        return dtBirthday;
    }

    public void setDtBirthday(LocalDate dtBirthday) {
        this.dtBirthday = dtBirthday;
    }

    public String getTxCity() {
        return txCity;
    }

    public void setTxCity(String txCity) {
        this.txCity = txCity;
    }

    public String getTxProfilePicture() {
        return txProfilePicture;
    }

    public void setTxProfilePicture(String txProfilePicture) {
        this.txProfilePicture = txProfilePicture;
    }

    public String getTxBiography() {
        return txBiography;
    }

    public void setTxBiography(String txBiography) {
        this.txBiography = txBiography;
    }
}
