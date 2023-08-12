package com.luv2code.bookmyshow.show;

import java.time.LocalDateTime;

public record ShowDTO(Integer showId, String showName, LocalDateTime showTime, int showAdditionalPrice){
        public static ShowDTO fromEntity(Show show) {
            return new ShowDTO(show.getId(), show.getShowName(), show.getShowTime(), show.getAdditionalPrice());
        }
    }