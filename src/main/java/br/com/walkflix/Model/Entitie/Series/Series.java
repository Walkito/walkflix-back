package br.com.walkflix.Model.Entitie.Series;

import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Character.Character;
import br.com.walkflix.Model.Entitie.Series.Episode.Episode;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 80)
    @NotBlank(message = "Nome da Série é obrigatório.")
    @Size(max = 80, message = "Nome da Série deve conter no máximo 80 caracteres")
    private String txSeriesName;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Nome do Diretor é obrigatório!")
    @Size(max = 50, message = "Nome do Diretor deve ter no máximo 50 caracteres.")
    private String txDirectorName;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Arte de Poster deve ser obrigatória.")
    private String txPicturePoster;

    @Column(length = 3)
    private int nuEpisode;

    @Column
    private LocalDate dtLaunch;

    @Column(length = 2)
    private int nuAgeClassification;

    @Column(columnDefinition = "TEXT")
    private String txResume;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Arte do Banner deve ser obrigatória.")
    private String txPictureBanner;

    @OneToMany(mappedBy = "firstSeries")
    private List<Actor> actorsFirstSeries = new ArrayList<>();

    @OneToMany(mappedBy = "series")
    private List<Episode> episodes = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<Character> characters = new ArrayList<>();

    public Series() {
    }

    public String getTxPicturePoster() {
        return txPicturePoster;
    }

    public void setTxPicturePoster(String txPicturePoster) {
        this.txPicturePoster = txPicturePoster;
    }

    public String getTxPictureBanner() {
        return txPictureBanner;
    }

    public void setTxPictureBanner(String txPictureBanner) {
        this.txPictureBanner = txPictureBanner;
    }

    public String getTxDirectorName() {
        return txDirectorName;
    }

    public void setTxDirectorName(String txDirectorName) {
        this.txDirectorName = txDirectorName;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
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

    public List<Actor> getActorsFirstSeries() {
        return actorsFirstSeries;
    }

    public void setActorsFirstSeries(List<Actor> actorsFirstSeries) {
        this.actorsFirstSeries = actorsFirstSeries;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
