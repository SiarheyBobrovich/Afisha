package by.it_academy.afisha.controllers;

import by.it_academy.afisha.dao.entity.Event;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.services.api.IAfishaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event")
public class EventController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IAfishaService service;

    public EventController(@Autowired IAfishaService service) {
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
    public List<Event> get(@PathVariable Type type) {
        return service.getEvents(type);
    }
}
