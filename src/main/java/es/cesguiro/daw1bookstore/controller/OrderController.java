package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(OrderController.URL)
public class OrderController {

    public static final String URL = "/orders";
    private final OrderService orderService;

    public OrderController() {
        this.orderService = OrderIoc.getOrderService();
    }
}
