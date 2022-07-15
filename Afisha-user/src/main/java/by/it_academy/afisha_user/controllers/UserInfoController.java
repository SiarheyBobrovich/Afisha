package by.it_academy.afisha_user.controllers;

import by.it_academy.afisha_user.controllers.utils.LocalDateTimeSerializer;
import by.it_academy.afisha_user.controllers.utils.LongToLocalDateTimeUtil;
import by.it_academy.afisha_user.dto.request.UserCreateDto;
import by.it_academy.afisha_user.services.api.IUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping(name = "/api/v1/users")
public class UserInfoController {

    private final IUserService service;

    public UserInfoController(IUserService service) {
        this.service = service;
    }

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @PostMapping
    public void save(@RequestBody UserCreateDto newUser) {
        service.save(newUser);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
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
