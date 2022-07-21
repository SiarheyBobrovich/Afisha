package by.it_academy.afisha.controllers.filter;

import by.it_academy.afisha.controllers.utils.JwtTokenUtil;
import by.it_academy.afisha.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Set;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final RestTemplate template;
    private final ObjectMapper mapper;

    public JwtFilter(RestTemplateBuilder template, ObjectMapper mapper) {
        this.template = template.build();
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!JwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        //Going to the user-service and getting information
        ClientHttpRequest httpRequest = template.getRequestFactory()
                .createRequest(URI.create("http://user-service:82/api/v1/users/me"), HttpMethod.GET);

        httpRequest.getHeaders()
                .put(HttpHeaders.AUTHORIZATION, List.of(header));

        UserDto user;

        try(ClientHttpResponse execute = httpRequest.execute()) {
            if (!execute.getStatusCode().is2xxSuccessful()) {
                throw new SecurityException("Пользователь был удалён");
            }

            user = mapper.readValue(execute.getBody(), UserDto.class);
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                user, null,
                user == null ?
                        List.of() : user.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
