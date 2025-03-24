package br.com.walkflix.Model.Entitie.Character;

import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Series.Series;
import br.com.walkflix.Model.Enum.CharacterStatus.CharacterStatus;
import br.com.walkflix.Model.Enum.CharacterType.CharacterType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 80, nullable = false)
    @NotBlank(message = "Nome do personagem é obrigatório!")
    @Size(max = 80, message = "Nome do Personagem deve ter no máximo 80 caracteres.")
    private String txCharacterName;

    @Column(nullable = false)
    @NotNull(message = "Status do Personagem é obrigatório.")
    private CharacterStatus tpCharacterStatus;

    @Column(nullable = false)
    @NotNull(message = "Papel do Personagem é obrigatório!")
    private CharacterType tpCharacterType;

    @ManyToOne(optional = false)
    @NotNull(message = "Ator é obrigatório.")
    private Actor actor;

    @Column(length = 3, nullable = false)
    @Min(value = 1, message = "Primeiro episódio deve ser no minimo 1.")
    @Max(value = 999, message = "Primeiro episódio deve ser no máximo 999.")
    private int nuFirstEpisode;

    @Column(length = 60)
    @Size(max = 60, message = "A Origem do personagem deve ter no máximo 60 caracteres.")
    private String txOrigin;

    @Column(length = 200)
    @Size(max = 200, message = "A Idade do personagem deve ter no máximo 200 caracteres.")
    private String txAge;

    @Column(columnDefinition = "TEXT")
    private String txBiography;

    @Column(columnDefinition = "TEXT")
    private String txCharacterPicture;

    @ManyToMany(mappedBy = "characters")
    private List<Series> series = new ArrayList<>();

    public Character() {
    }

    public void setTpCharacterStatus(CharacterStatus tpCharacterStatus) {
        this.tpCharacterStatus = tpCharacterStatus;
    }

    public CharacterType getTpCharacterType() {
        return tpCharacterType;
    }

    public CharacterStatus getTpCharacterStatus() {
        return tpCharacterStatus;
    }

    public void setTpCharacterType(CharacterType tpCharacterType) {
        this.tpCharacterType = tpCharacterType;
    }

    public String getTxCharacterPicture() {
        return txCharacterPicture;
    }

    public void setTxCharacterPicture(String txCharacterPicture) {
        this.txCharacterPicture = txCharacterPicture;
    }

    public Actor getActor() {
        return actor;
    }

    public String getTxBiography() {
        return txBiography;
    }

    public void setTxBiography(String txBiography) {
        this.txBiography = txBiography;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNuFirstEpisode() {
        return nuFirstEpisode;
    }

    public void setNuFirstEpisode(int nuFirstEpisode) {
        this.nuFirstEpisode = nuFirstEpisode;
    }

    public String getTxAge() {
        return txAge;
    }

    public void setTxAge(String txAge) {
        this.txAge = txAge;
    }

    public String getTxOrigin() {
        return txOrigin;
    }

    public void setTxOrigin(String txOrigin) {
        this.txOrigin = txOrigin;
    }

    public String getTxCharacterName() {
        return txCharacterName;
    }

    public void setTxCharacterName(String txCharacterName) {
        this.txCharacterName = txCharacterName;
    }
}
