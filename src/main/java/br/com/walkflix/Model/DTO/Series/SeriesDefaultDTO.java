package br.com.walkflix.Model.DTO.Series;

import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Character.Character;
import br.com.walkflix.Model.Entitie.Series.Episode.Episode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeriesDefaultDTO {
    private int id;

    private String txSeriesName;

    private ActorForSeriesResponseDTO director;

    private String txPicturePoster;

    private int nuEpisode;

    private LocalDate dtLaunch;

    private LocalDate dtClosure;

    private boolean tpActive;

    private int nuAgeClassification;

    private String txResume;

    private String txDescription;

    private String txPictureBanner;

    private String txPictureThumbnail;

    public SeriesDefaultDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxSeriesName() {
        return txSeriesName;
    }

    public void setTxSeriesName(String txSeriesName) {
        this.txSeriesName = txSeriesName;
    }

    public ActorForSeriesResponseDTO getDirector() {
        return director;
    }

    public void setDirector(ActorForSeriesResponseDTO director) {
        this.director = director;
    }

    public String getTxPicturePoster() {
        return txPicturePoster;
    }

    public void setTxPicturePoster(String txPicturePoster) {
        this.txPicturePoster = txPicturePoster;
    }

    public int getNuEpisode() {
        return nuEpisode;
    }

    public void setNuEpisode(int nuEpisode) {
        this.nuEpisode = nuEpisode;
    }

    public LocalDate getDtLaunch() {
        return dtLaunch;
    }

    public void setDtLaunch(LocalDate dtLaunch) {
        this.dtLaunch = dtLaunch;
    }

    public LocalDate getDtClosure() {
        return dtClosure;
    }

    public void setDtClosure(LocalDate dtClosure) {
        this.dtClosure = dtClosure;
    }

    public boolean isTpActive() {
        return tpActive;
    }

    public void setTpActive(boolean tpActive) {
        this.tpActive = tpActive;
    }

    public int getNuAgeClassification() {
        return nuAgeClassification;
    }

    public void setNuAgeClassification(int nuAgeClassification) {
        this.nuAgeClassification = nuAgeClassification;
    }

    public String getTxResume() {
        return txResume;
    }

    public void setTxResume(String txResume) {
        this.txResume = txResume;
    }

    public String getTxDescription() {
        return txDescription;
    }

    public void setTxDescription(String txDescription) {
        this.txDescription = txDescription;
    }

    public String getTxPictureBanner() {
        return txPictureBanner;
    }

    public void setTxPictureBanner(String txPictureBanner) {
        this.txPictureBanner = txPictureBanner;
    }

    public String getTxPictureThumbnail() {
        return txPictureThumbnail;
    }

    public void setTxPictureThumbnail(String txPictureThumbnail) {
        this.txPictureThumbnail = txPictureThumbnail;
    }
}
