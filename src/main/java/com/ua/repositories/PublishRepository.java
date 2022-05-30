package com.ua.repositories;

import com.ua.entities.PublishEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PublishRepository extends CrudRepository<PublishEntity, Long> {
    @Query(value = "SELECT * FROM publishes ORDER BY priority", nativeQuery = true)
    List<PublishEntity> findAll();
    Optional<PublishEntity> findByTitle(String title);
}
