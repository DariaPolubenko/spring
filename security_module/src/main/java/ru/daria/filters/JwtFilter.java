package ru.daria.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.daria.entity.Person;
import ru.daria.repository.PersonRepository;
import ru.daria.services.JwtUtil;

import java.io.IOException;
import java.util.Collections;


// OncePerRequestFilter - Фильтр, который гарантированно выполнится только один раз для каждого входящего запроса.
@Component
public class JwtFilter extends OncePerRequestFilter { //перехватывает каждый входящий http-запрос и ЕДИНОЖДЫ проверяет на наличие токен
    private final JwtUtil jwtUtil;
    private PersonRepository personRepository;

    public JwtFilter(JwtUtil jwtUtil, PersonRepository personRepository) {
        this.jwtUtil = jwtUtil;
        this.personRepository = personRepository;
    }

    // HttpServletRequest - Запрос
    // HttpServletResponse - Ответ, который сформирует бэк
    // FilterChain - Дальнейшие фильтры
    @Override// Authorization Bearer u3ijdffkjskjdskdksf.gkjdjfjjfjdjfd.sjfjsfjsfjsjf
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authoHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authoHeader != null && authoHeader.startsWith("Bearer ")) { // Bearer = jwt токен
            token = authoHeader.substring(7);
            username = jwtUtil.getUserNameFromToken(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //UserDetails userDetails = UserDetailsService
            Person person = personRepository.findByUserName(username);
            try {
                jwtUtil.validateToken(token);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(person, null, Collections.EMPTY_LIST);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        filterChain.doFilter(request, response);
    }
}
