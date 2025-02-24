package cz.st64113.nnpia25.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String password;

    @Column(nullable = false)
    private String email;
}
