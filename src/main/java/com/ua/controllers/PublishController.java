package com.ua.controllers;

import com.ua.entities.PublishEntity;
import com.ua.exceptions.PublishNotFoundException;
import com.ua.services.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishes")
@CrossOrigin(origins = "http://localhost:4200/")
public class PublishController {
    private final PublishService publishService;

    @Autowired
    public PublishController(PublishService publishService) {
        this.publishService = publishService;
    }

    @GetMapping
    public ResponseEntity<?> index(@RequestParam("publishId") Long id) {
        try {
            PublishEntity publishEntity = publishService.findById(id);
            return new ResponseEntity<>(publishEntity, HttpStatus.OK);
        } catch (PublishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PublishEntity>> findAll() {
        List<PublishEntity> publishes = publishService.findAll();
        return new ResponseEntity<>(publishes, HttpStatus.OK);
    }

    @GetMapping("/all/published")
    public ResponseEntity<List<PublishEntity>> findAllPublishes() {
        List<PublishEntity> publishes = publishService.findAllPublished();
        return new ResponseEntity<>(publishes, HttpStatus.OK);
    }

    @GetMapping("/all/unpublished")
    public ResponseEntity<List<PublishEntity>> findAllUnpublished() {
        List<PublishEntity> unpublished = publishService.findAllUnpublished();
        return new ResponseEntity<>(unpublished, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam("timestamp") String timestamp, @RequestBody() PublishEntity publish) {
        PublishEntity publishSaved = publishService.save(timestamp, publish);
        return publishSaved != null
                ? new ResponseEntity<>(publish, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam("publishId") Long id,
                                    @RequestBody PublishEntity publishEntity,
                                    @RequestParam("timestamp") String timestamp) {
        try {
            PublishEntity updated = publishService.update(id, publishEntity, timestamp);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (PublishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("publishId") Long id) {
        try {
            publishService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PublishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
