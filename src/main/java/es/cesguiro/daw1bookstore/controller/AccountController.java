package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.UserUtil;
import es.cesguiro.daw1bookstore.common.container.CartIoc;
import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.domain.model.Cart;
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
        List<Order> orderList = OrderIoc.getOrderService().findByUserId(UserUtil.getActiveUser().getId());
        model.addAttribute("orderList", orderList);
        return "orders/list";
    }

    @GetMapping("/cart")
    public String findCart(Model model) {
        //User user = getUser();
        User user = UserUtil.getActiveUser();
        model.addAttribute("cart", user.getCart());
        return "carts/detail";
    }
}
