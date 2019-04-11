package ru.bellintegrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.domain.Message;
import ru.bellintegrator.repos.MessageRepo;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService  {
    @Autowired
    private MessageRepo repo;

    @Override
    public Iterable<Message> findAll(){
        return repo.findAll();
    }

    @Override
    public void add(Message message) {
        repo.save(message);
    }

    @Override
    public List<Message> filter(String text) {
        return repo.findByTag(text);
    }
}
