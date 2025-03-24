package br.com.walkflix.Model.Enum.CharacterType;

public enum CharacterType {
    PROTAGONISTA("Protagonista"),
    COPROTAGONISTA("Coprotagonista"),
    COADJUVANTE("Coadjuvante"),
    ANTAGONISTA("Antagonista"),
    OPONENTE("Oponente"),
    FIGURANTE("Figurante");

    private String roleType;

    CharacterType(String roleType){
        this.roleType = roleType;
    }
}
