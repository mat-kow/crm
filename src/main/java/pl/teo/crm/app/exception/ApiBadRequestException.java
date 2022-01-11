package pl.teo.crm.app.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiBadRequestException extends RuntimeException {

    public ApiBadRequestException(String message) {
        super(message);
    }
}
