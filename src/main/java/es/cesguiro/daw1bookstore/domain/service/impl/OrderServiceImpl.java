package es.cesguiro.daw1bookstore.domain.service.impl;

import es.cesguiro.daw1bookstore.common.UserUtil;
import es.cesguiro.daw1bookstore.common.exception.AuthorizationException;
import es.cesguiro.daw1bookstore.common.exception.ResourceNotFoundException;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.OrderService;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order findById(Integer id) {

        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new ResourceNotFoundException("Order with id " + id + " not found.");
        }

        /*if(userPrincipal.getId() != order.getUser().getId()) {
            throw new AuthorizationException("You are not authorized to access this resource.");
        }*/

        return order;
    }

    @Override
    public Order findByIdAndUserId(Integer id, Integer userId) {
        User user = UserUtil.getActiveUser();

        Order order = orderRepository.findByIdAndUserId(id, userId);

        if (order == null) {
            throw new ResourceNotFoundException("Order with id " + id + " not found.");
        }

        return order;
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
