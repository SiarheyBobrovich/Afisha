package by.it_academy.afisha.controllers;

import by.it_academy.afisha.controllers.converters.PageEventConcertToPageEventDtoConverter;
import by.it_academy.afisha.controllers.converters.PageEventFilmxToPageEventDtoConverter;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.api.IEventDto;
import by.it_academy.afisha.dto.factories.EventDtoFactory;
import by.it_academy.afisha.services.api.IAfishaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event")
public class EventController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IAfishaService service;

    public EventController(IAfishaService service) {
        this.service = service;
    }

    @PostMapping(value = "/{type}")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody EventDtoFactory factory,
                     @PathVariable Type type) {

        if (!type.equals(factory.getType())) {
            throw new IllegalArgumentException("Типы не соотвествуют");
        }

        IEventDto eventDto = factory.getDto();
        service.save(eventDto, type);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{type}/{uuid}/dt_update/{dt_update}")
    public void update(@RequestBody EventDtoFactory factory,
                       @PathVariable Type type,
                       @PathVariable UUID uuid,
                       @PathVariable("dt_update") Long dtUpdate) {

        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.of("UTC"));

        service.update(factory.getDto(), type, uuid, date);
    }

    @GetMapping("/{type}")
    public ResponseEntity<Object> get(@PathVariable Type type,
                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "25") Integer size) {

        final PageRequest pageRequest = PageRequest.of(
                page, size, Sort.by("uuid")
        );

        final ResponseEntity<Object> body;

        if (Type.CONCERTS.equals(type)) {
            Page<EventConcert> concertEvents = service.getConcertEvents(type, pageRequest);
            body = ResponseEntity.ok(new PageEventConcertToPageEventDtoConverter().convert(concertEvents));

        } else if (Type.FILMS.equals(type)) {
            Page<EventFilm> filmEvents = service.getFilmEvents(type, pageRequest);
            body = ResponseEntity.ok(new PageEventFilmxToPageEventDtoConverter().convert(filmEvents));

        }else {
            throw new IllegalArgumentException("Данный тип не обслуживается");
        }

        return body;
    }
}
