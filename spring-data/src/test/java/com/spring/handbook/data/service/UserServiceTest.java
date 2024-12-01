package com.spring.handbook.data.service;

import com.spring.handbook.data.configuration.ApplicationConfig;
import com.spring.handbook.data.entity.Address;
import com.spring.handbook.data.entity.User;
import com.spring.handbook.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Stream;

@Testcontainers
@SpringBootTest
@Import(ApplicationConfig.class)
@ExtendWith({OutputCaptureExtension.class})
@TestPropertySource("classpath:application-test.properties")
class UserServiceTest {

    public static final String CREATING_NEW_TRANSACTION_LOG = "Creating new transaction with name ";
    private final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void saveUser(CapturedOutput output) {
        // When
        var user = userService.saveUser();

        // Then
        Assertions.assertNotNull(user);
        String[] logs = output.getOut().split("\r\n");
        var cnt = Stream.of(logs).filter(line -> line.contains(CREATING_NEW_TRANSACTION_LOG)).count();
        Assertions.assertEquals(1, cnt);
    }

    @Test
    public void updateInNonTransactionContext(CapturedOutput output) {
        // Given
        var user = new User();
        var address = new Address();
        address.setValue("01, LA");
        user.setAddress(address);
        userRepository.save(user);

        // When
        var updatedUser = userService.updateInNonTransactionContext(user.getId());

        // Then
        Assertions.assertNotNull(updatedUser);
        String[] logs = output.getOut().split("\r\n");
        var cnt = Stream.of(logs).filter(line -> line.contains(CREATING_NEW_TRANSACTION_LOG)).count();
        Assertions.assertEquals(4, cnt);
    }

    @Test
    public void update(CapturedOutput output) {
        // Given
        var user = new User();
        var address = new Address();
        address.setValue("01, LA");
        user.setAddress(address);
        userRepository.save(user);

        // When
        var updatedUser = userService.update(user.getId());

        // Then
        Assertions.assertNotNull(updatedUser);
        String[] logs = output.getOut().split("\r\n");

        //
        var cnt = Stream.of(logs).filter(line -> line.contains(CREATING_NEW_TRANSACTION_LOG)).count();
        Assertions.assertEquals(2, cnt);

        //
        var selectCnt = Stream.of(logs).filter(line -> line.contains("Hibernate:") && line.contains("select")).count();
        Assertions.assertEquals(1, selectCnt);
    }

}
