package br.com.walkflix.Model.DTO.Series.Episode;

import java.time.LocalDate;

public class EpisodeDTO {
    private int id;

    private int nuEpisode;

    private String txEpisodeName;

    private String txResume;

    private String txEpisodePicture;

    private LocalDate dtRelease;

    private int nuDuration;

    private int idSeries;

    public EpisodeDTO() {
    }

    public LocalDate getDtRelease() {
        return dtRelease;
    }

    public void setDtRelease(LocalDate dtRelease) {
        this.dtRelease = dtRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNuEpisode() {
        return nuEpisode;
    }

    public void setNuEpisode(int nuEpisode) {
        this.nuEpisode = nuEpisode;
    }

    public String getTxEpisodeName() {
        return txEpisodeName;
    }

    public void setTxEpisodeName(String txEpisodeName) {
        this.txEpisodeName = txEpisodeName;
    }

    public String getTxResume() {
        return txResume;
    }

    public void setTxResume(String txResume) {
        this.txResume = txResume;
    }

    public String getTxEpisodePicture() {
        return txEpisodePicture;
    }

    public void setTxEpisodePicture(String txEpisodePicture) {
        this.txEpisodePicture = txEpisodePicture;
    }

    public int getNuDuration() {
        return nuDuration;
    }

    public void setNuDuration(int nuDuration) {
        this.nuDuration = nuDuration;
    }

    public int getIdSeries() {
        return idSeries;
    }

    public void setIdSeries(int idSeries) {
        this.idSeries = idSeries;
    }
}
