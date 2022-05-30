package com.ua.services.impl;

import com.ua.entities.PublishEntity;
import com.ua.exceptions.PublishNotFoundException;
import com.ua.repositories.PublishRepository;
import com.ua.services.PublishService;
import com.ua.util.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublishServiceImpl implements PublishService {

    private final PublishRepository repository;
    private final DateParser parser;

    @Autowired
    public PublishServiceImpl(PublishRepository repository, DateParser parser) {
        this.repository = repository;
        this.parser = parser;
    }

    public PublishEntity findById(Long id) throws PublishNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(
                        ()-> new PublishNotFoundException("Cannot find the publish")
                );
    }

    @Override
    public PublishEntity findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<PublishEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<PublishEntity> findAllPublished() {
        Timestamp currentTimestamp = parser.getCurrentTimestamp();
        return repository
                .findAll()
                .stream()
                .filter(publish -> publish.getDate().compareTo(currentTimestamp) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublishEntity> findAllUnpublished() {
        Timestamp currentTimestamp = parser.getCurrentTimestamp();
        return repository
                .findAll()
                .stream()
                .filter(publish -> publish.getDate().compareTo(currentTimestamp) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public PublishEntity save(String date, PublishEntity publishEntity) {
        publishEntity.setDate(parser.parseToTimestamp(date));
        return repository.save(publishEntity);
    }

    @Override
    public PublishEntity update(Long id, PublishEntity publishEntity, String timestamp) throws PublishNotFoundException {
        PublishEntity entityToUpdate = findById(id);

        entityToUpdate.setTitle(publishEntity.getTitle());
        entityToUpdate.setDescription(publishEntity.getDescription());

        entityToUpdate.setText(publishEntity.getText());
        entityToUpdate.setPriority(publishEntity.getPriority());

        entityToUpdate.setDate(parser.parseToTimestamp(timestamp));
        return repository.save(entityToUpdate);
    }

    @Override
    public void delete(Long id) throws PublishNotFoundException {
        PublishEntity entityToDelete = findById(id);
        repository.delete(entityToDelete);
    }
}
