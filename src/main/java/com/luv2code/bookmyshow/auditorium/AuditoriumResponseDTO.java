package com.luv2code.bookmyshow.auditorium;

import com.luv2code.bookmyshow.feature.Feature;
import com.luv2code.bookmyshow.show.ShowResponseDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Auditorium}
 */
public record AuditoriumResponseDTO(Integer id, String name, int maxRow, int maxColumn, Set<Feature> supportedFeatures,
                                    Set<ShowResponseDTO> shows) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    public static AuditoriumResponseDTO fromEntity(Auditorium auditorium) {
        return new AuditoriumResponseDTO(auditorium.getId(),
                auditorium.getName(),
                auditorium.getMaxRow(),
                auditorium.getMaxColumn(),
                auditorium.getSupportedFeatures(),
                auditorium.getShows().stream().map(ShowResponseDTO::fromEntity).collect(Collectors.toSet()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AuditoriumResponseDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                this.maxRow == that.maxRow &&
                this.maxColumn == that.maxColumn &&
                Objects.equals(this.supportedFeatures, that.supportedFeatures) &&
                Objects.equals(this.shows, that.shows);
    }

    @Override
    public String toString() {
        return "AuditoriumResponseDTO[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "maxRow=" + maxRow + ", " +
                "maxColumn=" + maxColumn + ", " +
                "supportedFeatures=" + supportedFeatures + ", " +
                "shows=" + shows + ']';
    }

}