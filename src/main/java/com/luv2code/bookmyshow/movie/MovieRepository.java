package com.luv2code.bookmyshow.movie;

import com.luv2code.bookmyshow.city.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("""
            select m from Movie m inner join m.shows shows
            where shows.auditorium.theater.city = :city and shows.showTime > :showTime""")
    Page<Movie> findByShows_Auditorium_Theater_CityAndShows_ShowTimeGreaterThan(@Param("city") City city, @Param("showTime") LocalDateTime showTime, Pageable pageable);

}