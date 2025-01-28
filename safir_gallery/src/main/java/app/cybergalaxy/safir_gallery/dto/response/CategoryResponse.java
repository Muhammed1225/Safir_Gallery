package app.cybergalaxy.safir_gallery.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<CategorySingleResponse> categories;
}
