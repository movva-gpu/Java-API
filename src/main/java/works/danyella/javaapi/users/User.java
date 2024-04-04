package works.danyella.javaapi.users;

import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
import works.danyella.javaapi.tasks.Task;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Getter
@Setter
@Builder
public class User {

    @Builder.ObtainVia(method = "generateToken")
    private static String token;

    private String username;

    @Builder.ObtainVia(field = "password")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String passwordString;

    @Builder.ObtainVia(method = "generateSalt")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static String passwordHash;

    @Builder.ObtainVia(method = "generateHash")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static String passwordSalt;

    private String fullName;
    private String email;
    private String biography;
    private List<String> pronouns;
    private List<Map<Task, UserPermissions>> tasksPermissions;
    private String avatar;
    private String banner;
    private Date lastLogin;
    private Date creationDate;

    public static String generateToken() {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(
            UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
        );
    }

    private String generateSalt() {
        return BCrypt.gensalt();
    }

    private String generateHash() {
        return BCrypt.hashpw(this.passwordString, passwordSalt);
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, passwordHash);
    }

    public void setPassword(String password) {
        this.passwordString = password;
        passwordHash = BCrypt.hashpw(password, passwordSalt);
    }

    public String getPassword() {
        return this.passwordString;
    }
}
