package br.com.walkflix.Model.Enum.CharacterStatus;

public enum CharacterStatus {
    VIVO("Vivo"),
    INCERTO("Incerto"),
    MORTO("Morto");

    private final String status;

    CharacterStatus(String status){
        this.status = status;
    }
}
