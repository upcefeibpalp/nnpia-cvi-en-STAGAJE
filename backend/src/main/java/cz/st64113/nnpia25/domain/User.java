package cz.st64113.nnpia25.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
public class User {
    private long id;

    @ToString.Exclude
    private String password;

    private String email;
}
