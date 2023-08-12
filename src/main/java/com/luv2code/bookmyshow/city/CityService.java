package com.luv2code.bookmyshow.city;

import com.luv2code.bookmyshow.movie.MovieResponseDTO;
import com.luv2code.bookmyshow.movie.MovieService;
import com.luv2code.bookmyshow.theater.TheaterResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final MovieService movieService;

    public CityResponseDTO add(CityRequestDTO cityRequestDTO) {
        City city = cityRequestDTO.toEntity();
        return CityResponseDTO.fromEntity(cityRepository.save(city));
    }

    public List<City> findCities(String name) {
        return cityRepository.findByNameLikeIgnoreCase(name == null ? "" : name.toUpperCase());
    }

    public City getCityById(Integer cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException(
                "City with id [" + cityId  + "] not found"));
    }

    public Set<TheaterResponseDTO> getTheatersInCity(Integer cityId) {
        City city = cityRepository.findById(cityId).orElseThrow();
        return city.getTheaters().stream().map(TheaterResponseDTO::fromEntity).collect(Collectors.toSet());
    }

    public Page<MovieResponseDTO> getMoviesInCity(Integer cityId, Integer pageNo, Integer pageSize) {
        City city = cityRepository.findById(cityId).orElseThrow();
        return movieService.getMoviesByCityId(city, pageNo, pageSize);
    }
}
