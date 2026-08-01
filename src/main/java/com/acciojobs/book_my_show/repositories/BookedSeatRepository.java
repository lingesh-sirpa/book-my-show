package com.acciojobs.book_my_show.repositories;

import com.acciojobs.book_my_show.models.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BookedSeatRepository extends JpaRepository<BookedSeat, UUID> {

    @Query(value = "SELECT * FROM public. \"booked-seats\" where seat_id =:seatId and show_sys_id =:showSysId", nativeQuery = true)
    public BookedSeat isSeatBooked(String seatId, UUID showSysId);

}
