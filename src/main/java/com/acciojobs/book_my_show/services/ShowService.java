package com.acciojobs.book_my_show.services;

import com.acciojobs.book_my_show.dtos.SeatStatusResponse;
import com.acciojobs.book_my_show.dtos.ShowRequestDto;
import com.acciojobs.book_my_show.exceptions.UnAuthorizedException;
import com.acciojobs.book_my_show.models.*;
import com.acciojobs.book_my_show.repositories.BookedSeatRepository;
import com.acciojobs.book_my_show.repositories.ShowRepository;
import com.acciojobs.book_my_show.transformers.ApplicationTransformer;
import com.acciojobs.book_my_show.utilitis.SystemUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.ShortBufferException;
import javax.naming.directory.InvalidAttributesException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    private UserService userService;
    private HallService hallService;
    private ShowRepository showRepository;
    private ApplicationTransformer applicationTransformer;
    private BookedSeatRepository bookedSeatRepository;

    @Autowired
    public ShowService(UserService userService,
                       HallService hallService,
                       ShowRepository showRepository,
                       ApplicationTransformer applicationTransformer,
                       BookedSeatRepository bookedSeatRepository){
        this.userService = userService;
        this.hallService = hallService;
        this.showRepository = showRepository;
        this.applicationTransformer = applicationTransformer;
        this.bookedSeatRepository = bookedSeatRepository;
    }

    public boolean isOverLappingShow(List<Show> shows, Long startTime, Long endTime){
        Collections.sort(shows);
        for(Show show : shows){
            if(show.getEndTimeInSeconds() >= startTime){
                return true;
            }
        }
        return false;
    }



    public Show createShow(ShowRequestDto showRequestDto,
                           UUID hallSysId,
                           UUID userSysId) throws InvalidAttributesException {
        User user = userService.verifyTheaterOwner(userSysId);
        Hall hall = hallService.verifyHallSysId(hallSysId);
        if(!hall.getTheater().getOwner().getSysId().equals(user.getSysId())){
            throw new UnAuthorizedException("User is not allowed to create show in hall");
        }
        LocalDateTime startTime = showRequestDto.getStartTime();
        Long startTimeInSeconds = SystemUtility.convertShowTimeInSeconds(startTime);
        LocalDateTime endTime = showRequestDto.getEndTime();
        Long endTimeInSeconds = SystemUtility.convertShowTimeInSeconds(endTime);
        // Now we need to identify is this show over lapping with other shows of the hall
        List<Show> shows = showRepository.findByHall(hall);
        boolean isOverLapping = this.isOverLappingShow(shows, startTimeInSeconds, endTimeInSeconds);
        if(isOverLapping){
            throw new IllegalArgumentException("Overlapping timings");
        }
        Show show = applicationTransformer.transformShowDtoToShow(showRequestDto,
                hall,
                user,
                startTimeInSeconds,
                endTimeInSeconds);

        showRepository.save(show);
        return show;
    }

    public List<Show> searchShows(
            String movieName,
            String city
    ){
        List<Show> shows = showRepository.findByMovieName(movieName);
        List<Show> showsFilteredByCity = new ArrayList<>();
        for(Show show : shows){
            if(show.getHall().getTheater().getCity().equals(city)){
                showsFilteredByCity.add(show);
            }
        }

        return showsFilteredByCity;
    }

    public List<SeatStatusResponse> fetchSeatStatusByShowId(UUID showId) {
        Show show = showRepository.findById(showId).orElse(null);
        Hall hall = show.getHall();
        String rowRange = hall.getRowRange();
        int rowCapacity = hall.getSeatCapacity();
        String[] rowArr = rowRange.split("-");
        char stRange = rowArr[0].charAt(0);
        char enRange = rowArr[1].charAt(0);

        List<SeatStatusResponse> seatStatusResponses = new ArrayList<>();
        for(char ch = stRange; ch <= enRange; ch++){
            for(int i = 1; i <= rowCapacity; i++){
                String seatId = ch + "" + i;
                BookedSeat bookedSeat = bookedSeatRepository.isSeatBooked(seatId, show.getSysId());
                SeatStatusResponse seatStatusResponse = new SeatStatusResponse();
                seatStatusResponse.setSeatId(seatId);
                seatStatusResponse.setShow(show);
                if(bookedSeat == null){
                    seatStatusResponse.setSeatstatus("UNBOOKED");
                }else{
                    seatStatusResponse.setSeatstatus("BOOKED");
                }

                seatStatusResponses.add(seatStatusResponse);
            }
        }

        return seatStatusResponses;
    }


}