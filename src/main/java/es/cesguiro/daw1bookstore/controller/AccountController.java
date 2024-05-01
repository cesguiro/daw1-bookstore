package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(AccountController.URL)
public class AccountController {

    public static final String URL = "/account";

    @GetMapping("/orders")
    public String findOrders(Model model) {
        List<Order> orderList = OrderIoc.getOrderService().findByUserId(getUser().getId());
        model.addAttribute("orderList", orderList);
        return "orders/list";
    }

    private User getUser() {
        return UserIoc.getUserService().getActiveUser();
    }
}
