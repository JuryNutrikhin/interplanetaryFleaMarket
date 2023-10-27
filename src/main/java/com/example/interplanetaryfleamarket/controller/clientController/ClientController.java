package com.example.interplanetaryfleamarket.controller.clientController;


import com.example.interplanetaryfleamarket.entity.Client;
import com.example.interplanetaryfleamarket.servise.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("")
    public String findAll(Model model) {
        Iterable<Client> clients = clientService.findAll(); //получить список обьектов
        if (clients.iterator().hasNext()) {
            model.addAttribute("clients", clients); //довавить их в контекст
        } else {
            model.addAttribute("clients", null);
        }
        return "/clients/client-list";                        //вернуть представление
    }

    @GetMapping("new-client")
    public String addClient(Model model) {
        Client client = new Client();// обьект для заполнения
        model.addAttribute("client", client);
        return "/clients/client-form";
    }

    @PostMapping("new-client")
    public String addClient(Client client, RedirectAttributes redirectAttributes) {

        client.setRegistration(LocalDate.now());
        Optional<Client> newClient = clientService.add(client);
        if (newClient.isPresent()) {

            redirectAttributes.addFlashAttribute("successMessage", "Пользователь " + client.getClientName() + " успешно создан");
        } else {

            redirectAttributes.addFlashAttribute("errorMessage", "Пользователь " + client.getClientName() + " уже существует");

        }
        return "redirect:/clients";  //редирект на другую страницу
    }

    @GetMapping("delete/{id}")
    public String deleteId(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Client> removed = clientService.deleteById(id);
        if (removed.isPresent()) {
            redirectAttributes.addFlashAttribute("successMessage", "Пользователь " + removed.get().getClientName() + " успешно удален");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Некорректный id");
        }
        return "redirect:/clients";
    }

    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model) {
        Optional<Client> client = clientService.findById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
        } else {
            model.addAttribute("client", null);
        }
        return "/clients/clients-details";
    }
//Редактирование клиента

    @GetMapping("update/{id}")
    public String clientEditForm(@PathVariable Integer id, Model model) {
        Optional<Client> client = clientService.findById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
        } else {
            model.addAttribute("client", null);

        }
        return "/clients/client-edit";
    }

    @PostMapping("update/{id}")
    public String clientEditForm(@PathVariable Integer id,Client client, RedirectAttributes redirectAttributes) {

        Optional<Client> updated = clientService.updateById(id,client);// метод не работает

        if(updated.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage",
                    "Клиент успешно "+ updated.get().getClientName()+" успешно обнавлён");
        }else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Клиент не обновлён");
        }
        return "redirect:/clients";
    }


}
