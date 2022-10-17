package org.davo.security.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Class that represents the User table in DB. It has all the needed info for authorization and authentication.
 */
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //Eager cause I always want both user and all of the roles.
    private Collection<Role> roles = new ArrayList<>();
}
