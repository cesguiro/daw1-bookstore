package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.UserUtil;
import es.cesguiro.daw1bookstore.common.container.CartIoc;
import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping(CartController.URL)
public class CartController {

    public static final String URL = "carts";
    private final CartService cartService;

    public CartController() {
        this.cartService = CartIoc.getCartService();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String findById(Model model, @PathVariable int id) {
        Cart cart = cartService.findById(id);
        model.addAttribute("cart", cart);
        return "carts/detail";
    }

    @PostMapping("/books/{bookId}")
    public String addBook(@PathVariable int bookId, @RequestParam int quantity) {
        User user = UserUtil.getActiveUser();
        Cart cart = cartService.findByUserId(user.getId());
        Book book = new Book();
        book.setId(bookId);
        CartDetail cartDetail = new CartDetail(null, book, quantity, new BigDecimal(0.0));
        cartService.addCartDetail(cart, cartDetail);
        return "redirect:account/cart";
    }

    @DeleteMapping("/{cartDetailId}")
    public String removeCartDetail(@PathVariable int cartDetailId) {
        //User user = UserIoc.getUserService().getActiveUser();
        User user = UserUtil.getActiveUser();
        //Cart cart = cartService.findByUserId(user.getId())
        cartService.removeCartDetail(user.getCart(), cartDetailId);
        return "redirect:account/cart";
    }

    @PutMapping("/{cartDetailId}")
    public String updateCartDetail(@PathVariable int cartDetailId, @RequestParam int quantity) {
        //User user = UserIoc.getUserService().getActiveUser();
        User user = UserUtil.getActiveUser();
        Cart cart = cartService.findByUserId(user.getId());
        cartService.updateCartDetail(cart, cartDetailId, quantity);
        return "redirect:/cart";
    }

}
