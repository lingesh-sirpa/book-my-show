package com.acciojobs.book_my_show.dtos;

import com.acciojobs.book_my_show.models.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatStatusResponse {
    private Show show;
    private String seatId;
    private String seatstatus;
}
