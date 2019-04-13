package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.domain.Message;
import ru.bellintegrator.service.MessageService;

import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    private MessageService service;

    public MainController() {}

    @Autowired
    public MainController(MessageService service){
        this.service = service;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }

    @GetMapping("/home")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = service.findAll();

        model.put("messages", messages);
        return "home";
    }

    @PostMapping("/home")
    public String add(@RequestParam String text,@RequestParam String tag,Map<String, Object> model){
        Message message = new Message(text, tag);
        service.add(message);

        Iterable<Message> messages = service.findAll();
        model.put("messages", messages);
        return "home";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,Map<String, Object> model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
             messages = service.filter(filter);
        }else {
             messages = service.findAll();
        }

        model.put("messages", messages);
        return "home";
    }

}
