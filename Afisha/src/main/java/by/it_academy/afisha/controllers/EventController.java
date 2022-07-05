package by.it_academy.afisha.controllers;

import by.it_academy.afisha.dao.entity.Event;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;
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
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public void save(@RequestBody EventDto event, @PathVariable String type) {
        Type type1 = Enum.valueOf(Type.class, type);
        service.save(event, type1);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{type}/{uuid}/dt_update/{dt_update}")
    public void update(@RequestBody EventDto event, @PathVariable Type type,
                       @PathVariable UUID uuid,@PathVariable Long dt_update) {
        LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.of("UTC"));

        service.update(event, type, uuid, dtUpdate);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Event>> get(@PathVariable Type type,
                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(
                page == null ? 0 : page,
                size == null ? 25 : size,
                Sort.by("uuid")
        );

        Page<Event> events = service.getEvents(type, pageRequest);

        return ResponseEntity.ok().body(events.getContent());
    }
}
