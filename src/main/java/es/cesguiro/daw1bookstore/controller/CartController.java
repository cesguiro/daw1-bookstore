package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.container.CartIoc;
import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CartController.URL)
public class CartController {

    public static final String URL = "carts";
    private final CartService cartService;

    public CartController() {
        this.cartService = CartIoc.getCartService();
    }

    @GetMapping()
    public String findById(Model model) {
        //User user = UserIoc.getUserService().getActiveUser();
        //Cart cart = cartService.findByUserId(user.getId());
        Integer userId = 3;
        Cart cart = cartService.findByUserId(userId);
        model.addAttribute("cart", cart);
        return "carts/detail";
    }
}
