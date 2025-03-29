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
                order.setFlowerId(flowerId);
                order.setBasketId(basketId);
                orderRepository.save(order);
            } else {
                throw new MyException("The flower not found!");
            }
        }

        whatsAppService.sendWhatsAppMessage("Hello World", "https://www.google.com/imgres?q=safir%20wallpaper&imgurl=https%3A%2F%2Fpng.pngtree.com%2Fthumb_back%2Ffh260%2Fbackground%2F20240416%2Fpngtree-natural-sapphire-gemstone-jewel-or-gems-on-black-shine-color-image_15658802.jpg&imgrefurl=https%3A%2F%2Fid.pngtree.com%2Ffree-backgrounds-photos%2Fbatu-safir&docid=AQuzMYX5Ci087M&tbnid=z0W8x5SRktB0wM&vet=12ahUKEwjc563p5q6MAxWOVfEDHc1aH8YQM3oECDkQAA..i&w=720&h=404&hcb=2&ved=2ahUKEwjc563p5q6MAxWOVfEDHc1aH8YQM3oECDkQAA");
    }

}
