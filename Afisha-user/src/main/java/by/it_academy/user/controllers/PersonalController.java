package by.it_academy.user.controllers;

import by.it_academy.user.controllers.utils.JwtTokenUtil;
import by.it_academy.user.dto.request.UserLoginDto;
import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.services.api.IUserPersonalService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/users")
public class PersonalController {

    private final IUserPersonalService service;

    public PersonalController(IUserPersonalService service, PasswordEncoder encoder) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        this.service = service;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registration(@RequestBody UserRegistrationDto newUser) {
        service.save(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserLoginDto login) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,
                JwtTokenUtil.generateAccessToken(service.login(login))
        );

        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getInformationAbout(Principal principal) {

        return ResponseEntity.ok().body(service.getByMail(principal.getName()));
    }
}
