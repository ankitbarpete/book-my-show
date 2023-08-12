package com.luv2code.bookmyshow.auditorium;

import com.luv2code.bookmyshow.feature.Feature;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Auditorium}
 */
public record AuditoriumRequestDTO(String name, int maxRow, int maxColumn, Set<Feature> supportedFeatures,
                                   Integer theaterId) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AuditoriumRequestDTO) obj;
        return Objects.equals(this.name, that.name) &&
                this.maxRow == that.maxRow &&
                this.maxColumn == that.maxColumn &&
                Objects.equals(this.supportedFeatures, that.supportedFeatures) &&
                Objects.equals(this.theaterId, that.theaterId);
    }

    @Override
    public String toString() {
        return "AuditoriumRequestDTO[" +
                "name=" + name + ", " +
                "maxRow=" + maxRow + ", " +
                "maxColumn=" + maxColumn + ", " +
                "supportedFeatures=" + supportedFeatures + ", " +
                "theaterId=" + theaterId + ']';
    }

    /**
     *
     * @return Auditorium
     */
    public Auditorium toEntity() {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setMaxRow(maxRow);
        auditorium.setMaxColumn(maxColumn);
        auditorium.setSupportedFeatures(supportedFeatures);

        return auditorium;
    }
}