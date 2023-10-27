package com.example.interplanetaryfleamarket.controller.orderController;


import com.example.interplanetaryfleamarket.entity.Client;
import com.example.interplanetaryfleamarket.entity.Order;
import com.example.interplanetaryfleamarket.entity.Product;
import com.example.interplanetaryfleamarket.entity.User;
import com.example.interplanetaryfleamarket.servise.ClientService;
import com.example.interplanetaryfleamarket.servise.OrderService;
import com.example.interplanetaryfleamarket.servise.ProductService;
import com.example.interplanetaryfleamarket.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;


@RequiredArgsConstructor
@Controller
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final ClientService clientService;
    private final UserService userService;

    @GetMapping("")
    public String orderList(Model model) {
        Iterable<Order> orders = orderService.findAll();
        if (orders.iterator().hasNext()) {
            model.addAttribute("orders", orders);
        } else {
            model.addAttribute("orders", null);
        }
        return "order/order-list";
    }

    @GetMapping("/basket/{id}")
    public String orderClient(@PathVariable Integer id, Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String login = userDetails.getUsername();
        Optional<User> user = userService.findByLogin(login);
        Client client = clientService.findClientByUser(user);

        int count = 0;
        Iterable<Order> clientOrders;
        if (user.get().getRole().equals("ADMIN")  ) {

                clientOrders = orderService.findAllByClientId(id);

        } else {
            clientOrders = orderService.findAllByClientId(client.getId());
        }

        if (clientOrders.iterator().hasNext()) {


            for (Order order : clientOrders) {
                count = count + order.getProduct().getProductPrice();
            }
            model.addAttribute("count", count);
            model.addAttribute("clientOrders", clientOrders);
            model.addAttribute("client", client);
        } else {
            model.addAttribute("orders", null);
        }

        return "order/basket";
    }


    @GetMapping("add")

    public String addOrder(@RequestParam("productId") Integer productId, RedirectAttributes redirectAttributes) {


        //------------------------------------------------
        //узнаем логин из User для поиска id client
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = userDetails.getUsername();

        // получаем client по переменной в url

        Optional<User> user = userService.findByLogin(login);//ищем user
        Client client = clientService.findClientByUser(user);//ищем client
        //------------------------------------------------

        Optional<Product> newProduct = productService.findById(productId);
        Order newOrder = new Order();

        newOrder.setCount(1);
        newOrder.setOrderData(LocalDate.now());
        newOrder.setPayment(false);
        newOrder.setProduct(newProduct.get());
//        newOrder.setClient(null);
        newOrder.setClient(client);

        Order testOrder = orderService.add(newOrder);

        if (testOrder != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Заказ добавлен");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Заказ не добавлен");
        }
//        return "redirect:/order/{id}";
        return "redirect:/";
    }

    //Не работает
    @GetMapping("update/{id}")
    public String orderEditForm(@PathVariable Integer id, Model model) {
        Optional<Order> updated = orderService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("product", updated.get());
        } else {
            model.addAttribute("product", null);
        }
        return "/order/order-edit";
    }


    //не работает
    @PostMapping("update/{id}")
    public String orderEditForm(@PathVariable Integer id, Order order, RedirectAttributes redirectAttributes) {
        Optional<Order> updateOrder = orderService.updateById(id, order);
        if (updateOrder.isPresent()) {
            redirectAttributes.addFlashAttribute("successMessage",
                    "Заказ успешно обнавлен");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Заказ не изменён");
        }
        return "redirect:/order/basket";
    }

    @GetMapping("/delete/{id}")
    public String orderDeleteId(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Order> removed = orderService.deleteById(id);

        if (removed.isPresent()) {
            redirectAttributes.addFlashAttribute("successMessage", "Товар  успешно  удален");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Товар не удален ,некорректный id");
        }
        // определяем под кем авторизован
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = userDetails.getUsername();
        Optional<User> user = userService.findByLogin(login);//ищем user
        if(user.get().getRole().equals("ADMIN")){
            return  "redirect:/order";
        }
        return "redirect:/order/basket/-1";

    }

    @GetMapping("status/{id}")
    public String sale(@PathVariable Integer id) {
        Iterable<Order> clientOrders = orderService.findAllByClientId(id);
        for (Order order : clientOrders) {
            order.setPayment(true);
            orderService.add(order);
        }

        return "/order/sale";

    }
}
