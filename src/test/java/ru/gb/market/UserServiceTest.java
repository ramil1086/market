package ru.gb.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.models.User;
import ru.gb.market.repositories.UserRepository;
import ru.gb.market.services.UserService;

import java.util.Optional;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
@Autowired
    private UserService userService;

@MockBean
private UserRepository userRepository;

@Test
    public void FindOneUserTest() {
    User userFromDb = new User();
    userFromDb.setId(1L);
    userFromDb.setUsername("TestUser");
    userFromDb.setEmail("test@test.ru");
    Mockito.doReturn(Optional.of(userFromDb))
            .when(userRepository)
            .findByUsername("TestUser");
    User testUser = userService.findByUsername("TestUser").get();
    Assertions.assertNotNull(testUser);
    Assertions.assertEquals("test@test.ru", testUser.getEmail());
    Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("TestUser"));

}
}
