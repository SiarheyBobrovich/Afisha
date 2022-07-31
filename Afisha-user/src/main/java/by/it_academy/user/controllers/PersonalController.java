package by.it_academy.user.controllers;

import by.it_academy.user.dto.response.ResponseUserDto;
import by.it_academy.user.utils.JwtTokenUtil;
import by.it_academy.user.dto.request.UserLoginDto;
import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.services.api.IUserPersonalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
public class PersonalController {

    private final IUserPersonalService service;

    public PersonalController(IUserPersonalService service, PasswordEncoder encoder) {
        this.service = service;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registration(@RequestBody UserRegistrationDto newUser) {
        service.save(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login (@RequestBody UserLoginDto login) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,
                JwtTokenUtil.generateAccessToken(service.login(login))
        );

        return ResponseEntity.ok().headers(headers).build();
    }

    @ApiOperation(value = "Получить сведения о себе",
            response = ResponseUserDto.class,
            produces = "application/json", consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Запрос некорректен. Сервер не может обработать запрос"),
            @ApiResponse(responseCode = "401", description = "Для выполнения запроса на данный адрес требуется передать токен авторизации"),
            @ApiResponse(responseCode = "403", description = "Данному токенту авторизации запрещено выполнять запроса на данный адрес"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера. Сервер не смог корректно обработать запрос")
    })
    @GetMapping(value = "/me", produces = "application/json")
    @ResponseBody
    public ResponseUserDto getInformationAbout(Principal principal) {
        return service.getByMail(principal.getName());
    }
}
