package app.cybergalaxy.safir_gallery.service.impl;

import app.cybergalaxy.safir_gallery.dto.request.BasketAddRequest;
import app.cybergalaxy.safir_gallery.exception.MyException;
import app.cybergalaxy.safir_gallery.model.BasketEntity;
import app.cybergalaxy.safir_gallery.model.FlowerEntity;
import app.cybergalaxy.safir_gallery.model.OrderEntity;
import app.cybergalaxy.safir_gallery.repository.BasketRepository;
import app.cybergalaxy.safir_gallery.repository.FlowerRepository;
import app.cybergalaxy.safir_gallery.repository.OrderRepository;
import app.cybergalaxy.safir_gallery.service.inter.BasketInter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService implements BasketInter {

    @Autowired
    private WhatsAppService whatsAppService;

    @Autowired
    private BasketRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addOrder(BasketAddRequest request) {
        BasketEntity basket = new BasketEntity();
        basket.setClient(request.getClient());
        basket.setPhone(request.getPhone());
        basket.setDeliveryDate(request.getDeliveryDate());
        Integer basketId = repository.save(basket).getId();
        for (Integer flowerId : request.getFlowers()) {
            Optional<FlowerEntity> optional = flowerRepository.findById(flowerId);
            if (optional.isPresent()) {
                OrderEntity order = new OrderEntity();
                FlowerEntity flower = optional.get();
                order.setFlowerId(flowerId);
                order.setBasketId(basketId);
                orderRepository.save(order);

                whatsAppService.sendWhatsAppMediaMessage(
                        "Buketin adı: " + flower.getText() +
                                "\nBuketin qiyməti: " + flower.getPrice(),
                        "https://safirgallery-production.up.railway.app/uploads/" + flower.getImages().getFirst());
            } else {
                throw new MyException("The flower not found!");
            }
        }

        whatsAppService.sendWhatsAppMessage(
                "Müştəri məlumatları: " +
                "\nAd: " + request.getClient() +
                "\nTelefon: " + request.getPhone() +
                "\nÇatdırılma Tarixi: " + request.getDeliveryDate());
    }

}
