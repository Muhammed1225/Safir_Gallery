package app.cybergalaxy.safir_gallery.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class FlowerSingleResponse {

    private Integer id;

    private String text;

    private List<String> images;

}
