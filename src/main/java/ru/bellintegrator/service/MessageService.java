package ru.bellintegrator.service;

import ru.bellintegrator.domain.Message;

import java.util.List;

public interface MessageService {
    Iterable<Message> findAll();

    void add(Message message);

    List<Message> filter(String text);
}
