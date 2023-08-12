package com.luv2code.bookmyshow.show;

import com.luv2code.bookmyshow.feature.Feature;
import com.luv2code.bookmyshow.movie.MovieResponseDTO;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Show}
 */
public record ShowResponseDTO(Integer id, String showName, LocalDateTime showTime, Set<Feature> requiredFeatures,
                              int additionalPrice, MovieResponseDTO movie) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ShowResponseDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.showName, that.showName) &&
                Objects.equals(this.showTime, that.showTime) &&
                Objects.equals(this.requiredFeatures, that.requiredFeatures) &&
                this.additionalPrice == that.additionalPrice &&
                Objects.equals(this.movie, that.movie);
    }

    @Override
    public String toString() {
        return "ShowResponseDTO[" +
                "id=" + id + ", " +
                "showName=" + showName + ", " +
                "showTime=" + showTime + ", " +
                "requiredFeatures=" + requiredFeatures + ", " +
                "additionalPrice=" + additionalPrice + ", " +
                "movie=" + movie + ']';
    }

    public static ShowResponseDTO fromEntity(Show show) {
        return new ShowResponseDTO(
                show.getId(),
                show.getShowName(),
                show.getShowTime(),
                show.getRequiredFeatures(),
                show.getAdditionalPrice(),
                MovieResponseDTO.fromEntity(show.getMovie())
        );
    }
}