package es.cesguiro.daw1bookstore.domain.service.impl;

import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.service.OrderService;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRespository;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRespository orderRespository;

    public OrderServiceImpl(OrderRespository orderRespository) {
        this.orderRespository = orderRespository;
    }

    @Override
    public List<Order> findByUserId(Integer userId) {
        /*if(userId == null) {
            throw new AuthException("Not logged in");
        }
        UserRepository userRepository = UserIoc.getUserRepository();
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new ResourceNotFounException("User not found");
        }
        List<Order> orderList = orderRespository.findByUserId(userId);
        return orderList;*/
        return null;
    }
}
