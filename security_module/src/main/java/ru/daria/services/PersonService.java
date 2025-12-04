package ru.daria.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.daria.dto.response.authentication.ResponseAuthentication;
import ru.daria.entity.Person;
import ru.daria.exeptions.NotFoundException;
import ru.daria.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    //private AuthenticationManager authenticationManager;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        //this.authenticationManager = authenticationManager;
    }

    public Person createPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword())); //закодировали пароль
        personRepository.save(person);
        return person;
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

/*
    public String generateJwtToken(String userName, String password) {
        Authentication authenticator = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        if (authenticator.isAuthenticated()) {
            System.out.println("Вы успешно вошли");
            return jwtUtil.generateJwtToken(userName);
        } else {
            throw new IllegalArgumentException();
        }
    }
 */

    public ResponseAuthentication authentication(Person person) {
        Person dataBasePerson = personRepository.findByUserName(person.getUserName());
        if (personRepository.findByUserName(person.getUserName()) != null) {
            if (passwordEncoder.matches(person.getPassword(), dataBasePerson.getPassword())) {
                System.out.println("Пароль верный");
                String accessToken = jwtUtil.generateJwtToken(person.getUserName());
                String refreshToken = jwtUtil.generateRefreshJwtToken(person.getUserName());
                ResponseAuthentication responseAuthentication = new ResponseAuthentication(accessToken, refreshToken, "bearer");
                return responseAuthentication;
            }
        } else {
            throw new RuntimeException("Неверный пароль");
        }
        return null;
    }
}
