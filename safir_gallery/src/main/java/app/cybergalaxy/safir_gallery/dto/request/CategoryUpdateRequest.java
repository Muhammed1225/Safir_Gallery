package app.cybergalaxy.safir_gallery.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CategoryUpdateRequest {

    @NotNull
    @Positive
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

}
