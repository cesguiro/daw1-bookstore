package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(OrderController.URL)
public class OrderController {

    public static final String URL = "/orders";
    private final OrderService orderService;

    public OrderController() {
        this.orderService = OrderIoc.getOrderService();
    }

    @GetMapping
    public String findAllByUser(Authentication authentication, Model model) {
        if(authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        User user = (User) authentication.getPrincipal();
        List<Order> orderList = orderService.findByUserId(user.getId());
        model.addAttribute("orderList", orderList);
        return "orders/list";
    }
}
