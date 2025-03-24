package br.com.walkflix.Model.Enum.ActorStatus;

public enum ActorStatus {
    ATIVO("Ativo"),
    APOSENTADO("Aposentado");

    private final String status;

    ActorStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
