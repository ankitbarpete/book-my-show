package com.luv2code.bookmyshow.city;

import com.luv2code.bookmyshow.movie.MovieResponseDTO;
import com.luv2code.bookmyshow.theater.TheaterResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/cities"))
public class CityController {

    private final CityService cityService;
    @PostMapping()
    ResponseEntity<CityResponseDTO> add(@RequestBody CityRequestDTO cityRequestDTO) {
        CityResponseDTO savedCity = cityService.add(cityRequestDTO);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.
                        fromCurrentRequest()
                        .path("/"+savedCity.id())
                        .build()
                        .toUri())
                .body(savedCity);
    }

    @GetMapping
    ResponseEntity<List<CityResponseDTO>> getCities(@RequestParam(value = "name", required = false) String name) {
        List<CityResponseDTO> cities = cityService.findCities(name).stream().map(CityResponseDTO::fromEntity).toList();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{cityId}")
    ResponseEntity<CityResponseDTO> getCity(@PathVariable Integer cityId) {
        try {
            CityResponseDTO city = CityResponseDTO.fromEntity(cityService.getCityById(cityId));
            return ResponseEntity.ok(city);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{cityId}/theaters")
    ResponseEntity<Set<TheaterResponseDTO>> getTheatersInCity(@PathVariable Integer cityId) {
        Set<TheaterResponseDTO> theaters = cityService.getTheatersInCity(cityId);
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/{cityId}/movies")
    ResponseEntity<Page<MovieResponseDTO>> getMoviesInCity(@PathVariable Integer cityId,
                                                           @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<MovieResponseDTO> movieResponsePage = cityService.getMoviesInCity(cityId, pageNo, pageSize);
        return ResponseEntity.ok(movieResponsePage);
    }
}
