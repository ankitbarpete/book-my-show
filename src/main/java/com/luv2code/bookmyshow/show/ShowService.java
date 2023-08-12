package com.luv2code.bookmyshow.show;

import com.luv2code.bookmyshow.auditorium.Auditorium;
import com.luv2code.bookmyshow.auditorium.AuditoriumRepository;
import com.luv2code.bookmyshow.city.City;
import com.luv2code.bookmyshow.city.CityService;
import com.luv2code.bookmyshow.exception.NotEnoughFeaturesException;
import com.luv2code.bookmyshow.movie.Movie;
import com.luv2code.bookmyshow.movie.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final CityService cityService;
    private final MovieService movieService;

    public Show add(ShowRequestDTO showRequestDTO) throws NotEnoughFeaturesException {
        Auditorium auditorium = auditoriumRepository.findById(showRequestDTO.auditoriumId()).orElseThrow(() -> new EntityNotFoundException("auditorium with id [" + showRequestDTO.auditoriumId() + "] not found. Please check the id and try again."));
        if(auditorium.getSupportedFeatures().containsAll(showRequestDTO.requiredFeatures())) {
            Show show = showRequestDTO.toEntity();
            show.setAuditorium(auditorium);
            return showRepository.save(show);
        } else {
            throw new NotEnoughFeaturesException("Auditorium is not having all features to host this show."
                    + "\n Required features are: " + Arrays.toString(showRequestDTO.requiredFeatures().toArray())
                    + "\n Supported features are: " + Arrays.toString(auditorium.getSupportedFeatures().toArray())
            );
        }
    }

    public Slice<Show> getShowsByCityIdAndMovieId(Integer cityId, Integer movieId, Integer pageNo,
                                                  Integer pageSize) throws EntityNotFoundException {
        City city = cityService.getCityById(cityId);
        Movie movie = movieService.getMovieById(movieId);
        return showRepository.findByAuditorium_Theater_CityAndMovie(city, movie, PageRequest.of(pageNo, pageSize));
    }

    public Show getShowById(Integer showId) throws EntityNotFoundException {
        return showRepository.findById(showId).orElseThrow(EntityNotFoundException::new);
    }
}
