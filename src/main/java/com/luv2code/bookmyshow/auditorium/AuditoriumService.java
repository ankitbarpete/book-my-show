package com.luv2code.bookmyshow.auditorium;

import com.luv2code.bookmyshow.show.Show;
import com.luv2code.bookmyshow.show.ShowResponseDTO;
import com.luv2code.bookmyshow.theater.Theater;
import com.luv2code.bookmyshow.theater.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;
    private final TheaterRepository theaterRepository;

    public AuditoriumResponseDTO addAuditorium(AuditoriumRequestDTO auditoriumRequestDTO) {
        Theater theater = theaterRepository.findById(auditoriumRequestDTO.theaterId()).orElseThrow();
        Auditorium auditorium = auditoriumRequestDTO.toEntity();
        auditorium.setTheater(theater);
        Auditorium savedAuditorium = auditoriumRepository.save(auditorium);
        return AuditoriumResponseDTO.fromEntity(savedAuditorium);
    }

    public Set<ShowResponseDTO> getShowsInAuditorium(Integer auditoriumId) {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId).orElseThrow();
        Set<Show> shows = auditorium.getShows();
        return shows.stream().map(ShowResponseDTO::fromEntity).collect(Collectors.toSet());
    }
}
