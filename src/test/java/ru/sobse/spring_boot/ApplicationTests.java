package ru.sobse.spring_boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTests {
    private static final GenericContainer<?> appdev = new GenericContainer<>("appdev:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> appprod = new GenericContainer<>("appprod:latest")
            .withExposedPorts(8081);
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        appdev.start();
        appprod.start();
    }

    @Test
    void devProfileTest() {
        //arrange
        String expected = "Current profile is dev";
        //act
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + appdev.getMappedPort(8080), String.class);
        //assert
        Assertions.assertEquals(expected, forEntity.getBody());
    }

    @Test
    void prodProfileTest() {
        //arrange
        String expected = "Current profile is dev";
        //act
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + appprod.getMappedPort(8081), String.class);
        //assert
        Assertions.assertEquals(expected, forEntity.getBody());
    }

}
