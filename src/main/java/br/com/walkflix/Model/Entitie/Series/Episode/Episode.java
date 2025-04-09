package br.com.walkflix.Model.Entitie.Series.Episode;

import br.com.walkflix.Model.Entitie.Series.Series;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 3, nullable = false)
    @Min(value = 1, message = "Número do episódio deve ser entreo 0 e 100")
    @Max(value = 999, message = "Número do episódio deve ser entreo 0 e 100")
    private int nuEpisode;

    @Column(length = 70, nullable = false)
    @NotBlank(message = "Nome do episódio é obrigatório")
    @Size(max = 70, message = "Nome do episódio deve ter no máximo 70 caracteres.")
    private String txEpisodeName;

    @Column(columnDefinition = "TEXT")
    private String txResume;

    @Column(columnDefinition = "TEXT")
    private String txEpisodePicture;

    @Column
    private LocalDate dtRelease;

    @Column(length = 3, nullable = false)
    @Min(value = 1, message = "Número do episódio deve ser entreo 0 e 100")
    @Max(value = 999, message = "Número do episódio deve ser entreo 0 e 100")
    private int nuDuration;

    @ManyToOne(optional = false)
    @NotNull(message = "Episódio deve estar vínculado com alguma série.")
    private Series series;

    public Episode() {
    }

    public int getNuEpisode() {
        return nuEpisode;
    }

    public void setNuEpisode(int nuEpisode) {
        this.nuEpisode = nuEpisode;
    }

    public String getTxEpisodePicture() {
        return txEpisodePicture;
    }

    public void setTxEpisodePicture(String txEpisodePicture) {
        this.txEpisodePicture = txEpisodePicture;
    }

    public LocalDate getDtRelease() {
        return dtRelease;
    }

    public void setDtRelease(LocalDate dtRelease) {
        this.dtRelease = dtRelease;
    }

    public int getNuDuration() {
        return nuDuration;
    }

    public void setNuDuration(int nuDuration) {
        this.nuDuration = nuDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getTxResume() {
        return txResume;
    }

    public void setTxResume(String txResume) {
        this.txResume = txResume;
    }

    public String getTxEpisodeName() {
        return txEpisodeName;
    }

    public void setTxEpisodeName(String txEpisodeName) {
        this.txEpisodeName = txEpisodeName;
    }
}
