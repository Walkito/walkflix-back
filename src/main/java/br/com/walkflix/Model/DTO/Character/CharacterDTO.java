package br.com.walkflix.Model.DTO.Character;

import br.com.walkflix.Model.Enum.CharacterStatus.CharacterStatus;
import br.com.walkflix.Model.Enum.CharacterType.CharacterType;

public class CharacterDTO {
    private int id;

    private String txCharacterName;

    private CharacterStatus tpCharacterStatus;

    private CharacterType tpCharacterType;

    private int idActor;

    private int nuFirstEpisode;

    private String txOrigin;

    private String txAge;

    private String txBiography;

    private String txCharacterPicture;

    public CharacterDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxCharacterName() {
        return txCharacterName;
    }

    public void setTxCharacterName(String txCharacterName) {
        this.txCharacterName = txCharacterName;
    }

    public CharacterStatus getTpCharacterStatus() {
        return tpCharacterStatus;
    }

    public void setTpCharacterStatus(CharacterStatus tpCharacterStatus) {
        this.tpCharacterStatus = tpCharacterStatus;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public CharacterType getTpCharacterType() {
        return tpCharacterType;
    }

    public void setTpCharacterType(CharacterType tpCharacterType) {
        this.tpCharacterType = tpCharacterType;
    }

    public int getNuFirstEpisode() {
        return nuFirstEpisode;
    }

    public void setNuFirstEpisode(int nuFirstEpisode) {
        this.nuFirstEpisode = nuFirstEpisode;
    }

    public String getTxOrigin() {
        return txOrigin;
    }

    public void setTxOrigin(String txOrigin) {
        this.txOrigin = txOrigin;
    }

    public String getTxAge() {
        return txAge;
    }

    public void setTxAge(String txAge) {
        this.txAge = txAge;
    }

    public String getTxBiography() {
        return txBiography;
    }

    public void setTxBiography(String txBiography) {
        this.txBiography = txBiography;
    }

    public String getTxCharacterPicture() {
        return txCharacterPicture;
    }

    public void setTxCharacterPicture(String txCharacterPicture) {
        this.txCharacterPicture = txCharacterPicture;
    }
}
