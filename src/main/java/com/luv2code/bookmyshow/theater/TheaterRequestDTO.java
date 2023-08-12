package com.luv2code.bookmyshow.theater;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Theater}
 */
public record TheaterRequestDTO(String name, String address, Integer cityId) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TheaterRequestDTO) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.cityId, that.cityId);
    }

    @Override
    public String toString() {
        return "TheaterRequestDTO[" +
                "name=" + name + ", " +
                "address=" + address + ", " +
                "cityId=" + cityId + ']';
    }

    public Theater toEntity() {
        Theater theater = new Theater();
        theater.setName(this.name);
        theater.setAddress(this.address);
        return theater;
    }
}