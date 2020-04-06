package com.example.monitoring;

import com.example.monitoring.model.Person;
import com.example.monitoring.repository.PersonRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class MonitoringApplicationTests {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private PersonRepository repo;

    @Test()
    void should_save_person() {
        //given
        var person = new Person("John", Instant.now().toString(), 35);

        //when
        var response = template.postForEntity("/person", person, Person.class);

        //then
        assertThat(response.getBody().getAge()).isEqualTo(person.getAge());
    }

    @Test
    void should_receive_person() {
        //given
        var person = new Person("John", Instant.now().toString(), 35);
        repo.save(person);

        //when
        var response = template.getForEntity("/person", List.class);

        //then
        assertThat(response.getBody().size()).isEqualTo(1);
    }
}
