package br.com.walkflix.Model.Entitie.Actor;

import br.com.walkflix.Model.Entitie.Character.Character;
import br.com.walkflix.Model.Entitie.Series.Series;
import br.com.walkflix.Model.Enum.ActorStatus.ActorStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actors")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Nome do Ator é obrigatório!")
    @Size(max = 30, message = "Nome do Ator deve deve ter no máximo 30 caracteres.")
    private String txActorName;

    @Column(length = 60, nullable = false)
    @NotBlank(message = "Sobrenome do Ator é obrigatório!")
    @Size(max = 60, message = "Sobrenome do Ator deve ter no máximo 60 caracteres.")
    private String txActorSurname;

    @Column(nullable = false)
    @NotNull(message = "Status é obrigatório!")
    private ActorStatus status;

    @Column
    private LocalDate dtBirthday;

    @Column(length = 80)
    @Size(max = 80, message = "Cidade do Ator deve ter no máximo 80 caracteres.")
    private String txCity;

    @Column(columnDefinition = "TEXT")
    private String txProfilePicture;

    @Column(columnDefinition = "TEXT")
    private String txBiography;

    @ManyToOne
    private Series firstSeries;

    @ManyToMany(mappedBy = "actors")
    private List<Series> series = new ArrayList<>();

    @OneToMany(mappedBy = "actor")
    private List<Character> characters = new ArrayList<>();

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
    private List<Series> directedSeries = new ArrayList<>();

    public Actor() {
    }

    public List<Series> getDirectedSeries() {
        return directedSeries;
    }

    public void setDirectedSeries(List<Series> directedSeries) {
        this.directedSeries = directedSeries;
    }

    public String getTxActorSurname() {
        return txActorSurname;
    }

    public void setTxActorSurname(String txActorSurname) {
        this.txActorSurname = txActorSurname;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public String getTxBiography() {
        return txBiography;
    }

    public void setTxBiography(String txBiography) {
        this.txBiography = txBiography;
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

    public Series getFirstSeries() {
        return firstSeries;
    }

    public void setFirstSeries(Series firstSeries) {
        this.firstSeries = firstSeries;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public ActorStatus getStatus() {
        return status;
    }

    public void setStatus(ActorStatus status) {
        this.status = status;
    }
}
