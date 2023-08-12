package com.luv2code.bookmyshow.city;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link City}
 */
public record CityRequestDTO(String name) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     *
     */
    public CityRequestDTO {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CityRequestDTO) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public String toString() {
        return "CityRequestDTO[" +
                "name=" + name + ']';
    }

    City toEntity() {
        City city = new City();
        city.setName(this.name);
        return city;
    }

}