package com.luv2code.bookmyshow.city;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("select c from City c where upper(c.name) like %:name%")
    List<City> findByNameLikeIgnoreCase(@Param("name") @Nonnull String name);

}