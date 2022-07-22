package by.it_academy.afisha.controllers;

import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventConcertDto;
import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.dto.EventFilmDto;
import by.it_academy.afisha.exceptions.TypeNotSupportedException;
import by.it_academy.afisha.services.api.IAfishaService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

/**
 * Afisha-events controller
 */
@RestController
@RequestMapping("/api/v1/afisha/event")
public class EventController {

    private final IAfishaService service;

    public EventController(IAfishaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody EventDto dto, Principal userDetails) {
        String name = userDetails.getName();

        switch (dto.getType()) {
            case FILMS:
                service.save((EventFilmDto) dto, name);
                break;

            case CONCERTS:
                service.save((EventConcertDto) dto, name);
                break;

            default: throw new TypeNotSupportedException(dto.getType());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@RequestBody EventDto dto,
                       @PathVariable UUID uuid,
                       @PathVariable("dt_update") Long dtUpdate,
                       Principal userDetails) {
        String name = userDetails.getName();

        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.of("UTC"));

        switch (dto.getType()) {
            case FILMS:
                service.update((EventFilmDto) dto, uuid, date, name);
                break;

            case CONCERTS:
                service.update((EventConcertDto) dto, uuid, date, name);
                break;

            default: throw new TypeNotSupportedException(dto.getType());
        }
    }

    @GetMapping("/{type}")
    public ResponseEntity<Object> get(@PathVariable Type type,
                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "25") Integer size) {

        final PageRequest pageRequest = PageRequest.of(
                page, size, Sort.by("uuid")
        );

        final ResponseEntity<Object> body;

        if (Type.FILMS.equals(type)) {
            body = ResponseEntity.ok(service.getEventFilms(pageRequest));

        }else if (Type.CONCERTS.equals(type)) {
            body = ResponseEntity.ok(service.getEventConcerts(pageRequest));

        }else {
            throw new TypeNotSupportedException(type);
        }

        return body;
    }
}
