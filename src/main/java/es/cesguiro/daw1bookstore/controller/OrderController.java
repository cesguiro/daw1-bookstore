package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(OrderController.URL)
public class OrderController {

    public static final String URL = "/orders";
    private final OrderService orderService;

    private final MessageSource messageSource;

    @Autowired
    public OrderController(MessageSource messageSource) {
        this.messageSource = messageSource;
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

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable int id) {
        Order order = orderService.findById(id);

        //Modificar el formato de fecha en función del idioma
        Locale locale = LocaleContextHolder.getLocale();
        // Obtener el patrón de formato de fecha según el idioma local
        String dateFormatPattern = messageSource.getMessage("date.format", null, locale);
        // Formatear las fechas de la orden según el patrón de formato obtenido
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormatPattern, locale);
        String orderDateFormatted = order.getOrderDate().format(dateFormatter);
        String deliveryDateFormatted = "";
        if(order.getDeliveryDate() != null) {
            deliveryDateFormatted = order.getDeliveryDate().format(dateFormatter);
        }

        // Agregar las fechas formateadas al modelo
        model.addAttribute("orderDate", orderDateFormatted);
        model.addAttribute("deliveryDate", deliveryDateFormatted);
        model.addAttribute("order", order);
        return "orders/detail";
    }
}
