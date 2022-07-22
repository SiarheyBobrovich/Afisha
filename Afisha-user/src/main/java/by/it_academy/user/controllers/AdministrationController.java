package by.it_academy.user.controllers;

import by.it_academy.user.utils.LongToLocalDateTimeUtil;
import by.it_academy.user.dto.request.UserCreateDto;
import by.it_academy.user.services.api.IAdministrationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AdministrationController {

    private final IAdministrationService service;

    public AdministrationController(IAdministrationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody UserCreateDto newUser) {
        service.save(newUser);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void update(@RequestBody UserCreateDto newUser,
                @PathVariable UUID uuid,
                @PathVariable(name = "dt_update") Long dtUpdate) {

        LocalDateTime version = LongToLocalDateTimeUtil.fromLong(dtUpdate);

        service.update(newUser, uuid, version);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "20") Integer size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("nick"));

        return ResponseEntity.ok().body(service.getAll(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getById(@PathVariable UUID uuid) {

        return ResponseEntity.ok().body(service.get(uuid));
    }
}
