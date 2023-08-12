package com.luv2code.bookmyshow.show;

import com.luv2code.bookmyshow.feature.Feature;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Show}
 */
public record ShowRequestDTO(String showName, LocalDateTime showTime, Set<Feature> requiredFeatures,
                             int additionalPrice, Integer auditoriumId, Integer movieId) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ShowRequestDTO) obj;
        return Objects.equals(this.showName, that.showName) &&
                Objects.equals(this.showTime, that.showTime) &&
                Objects.equals(this.requiredFeatures, that.requiredFeatures) &&
                this.additionalPrice == that.additionalPrice &&
                Objects.equals(this.auditoriumId, that.auditoriumId) &&
                Objects.equals(this.movieId, that.movieId);
    }

    @Override
    public String toString() {
        return "ShowRequestDTO[" +
                "showName=" + showName + ", " +
                "showTime=" + showTime + ", " +
                "requiredFeatures=" + requiredFeatures + ", " +
                "additionalPrice=" + additionalPrice + ", " +
                "auditoriumId=" + auditoriumId + ", " +
                "movieId=" + movieId + ']';
    }

    public Show toEntity() {
        Show show = new Show();
        show.setShowName(showName);
        show.setShowTime(showTime);
        show.setRequiredFeatures(requiredFeatures);
        show.setAdditionalPrice(additionalPrice);
        return show;
    }
}