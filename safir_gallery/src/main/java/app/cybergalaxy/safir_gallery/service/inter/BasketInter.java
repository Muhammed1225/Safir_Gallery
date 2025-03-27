package app.cybergalaxy.safir_gallery.service.inter;

import java.time.LocalDate;
import java.util.List;

public interface BasketInter {
    void addOrder(String client, String phone, List<Integer> flowers, LocalDate deliveryDate);
}
