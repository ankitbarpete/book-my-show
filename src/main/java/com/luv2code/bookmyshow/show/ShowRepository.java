package com.luv2code.bookmyshow.show;

import com.luv2code.bookmyshow.city.City;
import com.luv2code.bookmyshow.movie.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    Slice<Show> findByAuditorium_Theater_CityAndMovie(City city, Movie movie, Pageable pageable);
}