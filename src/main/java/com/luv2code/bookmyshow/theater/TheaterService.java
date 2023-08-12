package com.luv2code.bookmyshow.theater;

import com.luv2code.bookmyshow.auditorium.AuditoriumResponseDTO;
import com.luv2code.bookmyshow.city.City;
import com.luv2code.bookmyshow.city.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final CityRepository cityRepository;

    public TheaterResponseDTO save(TheaterRequestDTO theaterRequestDTO) {
        City city = cityRepository.findById(theaterRequestDTO.cityId()).orElseThrow();
        Theater theater = theaterRequestDTO.toEntity();
        theater.setCity(city);
        return TheaterResponseDTO.fromEntity(theaterRepository.save(theater));
    }

    public TheaterResponseDTO getTheaterById(Integer theaterId) {
        return TheaterResponseDTO.fromEntity(theaterRepository.findById(theaterId).orElseThrow());
    }

    public Set<AuditoriumResponseDTO> getAuditoriumsInTheater(Integer theaterId) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow();
        return theater.getAuditoriums()
                .stream()
                .map(AuditoriumResponseDTO::fromEntity)
                .collect(Collectors.toSet());
    }
}
