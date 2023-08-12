package com.luv2code.bookmyshow.city;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link City}
 */
public record CityResponseDTO(Integer id, String name) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     *
     */
    public CityResponseDTO {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CityResponseDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name);
    }

    @Override
    public String toString() {
        return "CityResponseDTO[" +
                "id=" + id + ", " +
                "name=" + name + ", ";
    }

    static CityResponseDTO fromEntity(City city) {
        return new CityResponseDTO(city.getId(), city.getName());
    }
}