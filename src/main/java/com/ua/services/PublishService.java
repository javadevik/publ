package com.ua.services;

import com.ua.entities.PublishEntity;
import com.ua.exceptions.PublishNotFoundException;

import java.util.List;

public interface PublishService {
    /**
     * Find publish by id in database
     * @param id publish's identifier
     * @return publish have been found
     * @throws PublishNotFoundException when cannot find publish
     */
    PublishEntity findById(Long id) throws PublishNotFoundException;

    /**
     * Find all publishes in database
     * Publishes are sorted by priority
     * @return list of publishes
     */
    List<PublishEntity> findAll();

    /**
     * Find all publishes which have been published in database
     * Publishes are sorted by priority
     * @return list of publish
     */
    List<PublishEntity> findAllPublished();

    /**
     * Find all publishes which haven't been published in database
     * Publishes are sorted by priority
     * @return list of unpublished publish
     */
    List<PublishEntity> findAllUnpublished();

    /**
     * Save created publish to database
     * @param publishEntity entity to save
     * @param date time and date of publish at
     * @return publish have been saved
     */
    PublishEntity save(String date, PublishEntity publishEntity);

    /**
     * Update publish
     * @param id publish's identifier to update
     * @param publishEntity new publish entity
     * @return publish have been updated
     * @throws PublishNotFoundException when cannot find publish to update
     */
    PublishEntity update(Long id, PublishEntity publishEntity, String timestamp) throws PublishNotFoundException;

    /**
     * Delete publish by id
     * @param id publish's identifier to delete
     * @throws PublishNotFoundException when cannot find publish to delete
     */
    void delete(Long id) throws PublishNotFoundException;
}
