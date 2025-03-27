package app.cybergalaxy.safir_gallery.service.impl;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService implements BasketInter {

    @Autowired
    private BasketRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addOrder(String client, String phone, List<Integer> flowers, LocalDate deliveryDate) {
        BasketEntity basket = new BasketEntity();
        basket.setClient(client);
        basket.setPhone(phone);
        basket.setDeliveryDate(deliveryDate);
        Integer basketId = repository.save(basket).getId();
        for (Integer flowerId : flowers) {
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
    }

}
