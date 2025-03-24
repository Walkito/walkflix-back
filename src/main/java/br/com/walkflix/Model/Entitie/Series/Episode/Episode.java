package br.com.walkflix.Model.Entitie.Series.Episode;

import br.com.walkflix.Model.Entitie.Series.Series;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 70, nullable = false)
    @NotBlank(message = "Nome do episódio é obrigatório")
    @Size(max = 70, message = "Nome do episódio deve ter no máximo 70 caracteres.")
    private String txEpisodeName;

    @Column(columnDefinition = "TEXT")
    private String txResume;

    @Column
    private String txEpisodePicture;

    @Column
    private LocalDate dtRelease;

    @Column
    private String txDuration;

    @ManyToOne(optional = false)
    @NotNull(message = "Episódio deve estar vínculado com alguma série.")
    private Series series;

    public Episode() {
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

    public String getTxDuration() {
        return txDuration;
    }

    public void setTxDuration(String txDuration) {
        this.txDuration = txDuration;
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
