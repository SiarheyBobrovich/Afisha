package by.it_academy.afisha.controllers;

import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.PageDtos;
import by.it_academy.afisha.dto.RequestEventDto;
import by.it_academy.afisha.services.EventServiceSwitcher;
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

    private final EventServiceSwitcher switcher;

    public EventController(EventServiceSwitcher switcher) {
        this.switcher = switcher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody RequestEventDto dto, Principal userDetails) {
        String userName = userDetails.getName();

        switcher.save(dto, userName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@RequestBody RequestEventDto dto,
                       @PathVariable UUID uuid,
                       @PathVariable("dt_update") Long dtUpdate,
                       Principal userDetails) {
        String userName = userDetails.getName();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.of("UTC"));

        switcher.update(dto, uuid, localDateTime, userName);
    }

    @GetMapping("/{type}")
    public ResponseEntity<Object> getAll(@PathVariable Type type,
                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "20") Integer size) {

        final PageRequest pageRequest = PageRequest.of(
                page, size
        );

        return ResponseEntity.ok()
                .body(PageDtos.of(switcher.getAllEvents(type, pageRequest)));
    }

    @GetMapping("/{type}/{uuid}")
    public ResponseEntity<Object> getByUUID(@PathVariable Type type,
                                      @PathVariable UUID uuid) {

        return ResponseEntity.ok()
                .body(switcher.getSingleEvent(type, uuid));
    }
}
