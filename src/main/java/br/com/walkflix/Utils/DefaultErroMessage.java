package br.com.walkflix.Utils;

import br.com.walkflix.Model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultErroMessage {

    public static ResponseEntity<ApiResponse> getDefaultError(Exception e){
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ApiResponse(
                "Ocorreu um erro no sistema: " + e.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        ));
    }
}
