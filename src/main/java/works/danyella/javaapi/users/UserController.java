package works.danyella.javaapi.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> users() {
        return Collections.singletonList(
            new User.UserBuilder()
                .username("danyella")
                .passwordString("password")
                .fullName("Danyella")
                .email("test@test")
                .biography("This is a test biography")
                .pronouns(new ArrayList<>(Arrays.asList("she/her", "ae/aer")))
                .build()
        );
    }

    @PostMapping("/users")
    public void createUser(User user) {
        // TODO: Implement user creation and more
    }

}
