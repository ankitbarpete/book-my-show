package com.luv2code.bookmyshow.user;

import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.ticket.Ticket;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "t_user")
public class User extends GenericEntity {
    private String name;
    private String role;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Ticket> tickets = new LinkedHashSet<>();

}
