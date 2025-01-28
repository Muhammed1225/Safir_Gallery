package app.cybergalaxy.safir_gallery.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryAddRequest {
    @NotNull
    @NotBlank
    private String name;
}
