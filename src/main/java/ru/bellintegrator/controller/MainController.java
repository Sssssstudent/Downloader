package ru.bellintegrator.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.domain.Message;
import ru.bellintegrator.domain.User;
import ru.bellintegrator.service.MessageService;

import java.util.Map;


@Controller
public class MainController {
    private final MessageService service;

    public MainController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/home")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = service.filter(filter);
        } else {
            messages = service.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "home";
    }

    @PostMapping("/home")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        service.add(message);

        Iterable<Message> messages = service.findAll();
        model.put("messages", messages);

        return "home";
    }

}
