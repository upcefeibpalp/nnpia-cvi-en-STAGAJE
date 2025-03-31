package cz.st64113.nnpia25.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Data
public class User /* implements UserDetails */ {
    @Id
    private long id;

    @Column(nullable = false)
    @ToString.Exclude
    @NotBlank(message = "User password is required!")
    private String password;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "User email is required!")
    @Email(message = "Invalid email format!")
    private String email;

    @Column(nullable = false)
    private boolean active = true;

    public User(long id, String email, String password, boolean active) {
        this.id = id;
        this.email = email;
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());   // TODO: hash?
        this.active = active;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
}
