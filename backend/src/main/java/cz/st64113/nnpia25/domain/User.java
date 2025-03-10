package cz.st64113.nnpia25.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private long id;

    @Column(nullable = false)
    @ToString.Exclude
    @NotBlank(message = "User password is required!")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "User email is required!")
    @Email(message = "Invalid email format!")
    private String email;
}
