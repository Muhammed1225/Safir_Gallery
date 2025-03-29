package app.cybergalaxy.safir_gallery.service.inter;

import app.cybergalaxy.safir_gallery.dto.request.BasketAddRequest;

public interface BasketInter {
    void addOrder(BasketAddRequest request);
}
