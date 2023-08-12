package com.luv2code.bookmyshow.city;

import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.theater.Theater;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class City extends GenericEntity {
    private String name;

    @OneToMany(mappedBy = "city", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Theater> theaters = new LinkedHashSet<>();

}
