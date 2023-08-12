package com.luv2code.bookmyshow.theater;

import com.luv2code.bookmyshow.auditorium.AuditoriumResponseDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Theater}
 */
public record TheaterResponseDTO(Integer id, String name, String address,
                                 Set<AuditoriumResponseDTO> auditoriums) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     *
     */
    public TheaterResponseDTO {
    }

    public static TheaterResponseDTO fromEntity(Theater theater) {
        return new TheaterResponseDTO(theater.getId(),
                theater.getName(),
                theater.getAddress(),
                theater.getAuditoriums().stream()
                        .map(AuditoriumResponseDTO::fromEntity).collect(Collectors.toSet())
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TheaterResponseDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.auditoriums, that.auditoriums);
    }

    @Override
    public String toString() {
        return "TheaterResponseDTO[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "address=" + address + ", " +
                "auditoriums=" + auditoriums + ']';
    }

}