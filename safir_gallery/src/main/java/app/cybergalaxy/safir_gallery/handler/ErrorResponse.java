package app.cybergalaxy.safir_gallery.handler;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private String message;
    private List<MyFieldError> fieldErrors;
}
