package com.acciojobs.book_my_show.controllers;

import com.acciojobs.book_my_show.dtos.BookSeatDto;
import com.acciojobs.book_my_show.dtos.ShowRequestDto;
import com.acciojobs.book_my_show.exceptions.UnAuthorizedException;
import com.acciojobs.book_my_show.models.BookedSeat;
import com.acciojobs.book_my_show.models.Show;
import com.acciojobs.book_my_show.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;
import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/show")
public class ShowController {

    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService = showService;
    }

    @PostMapping("/create")
    public ResponseEntity createShow(
            @RequestParam UUID hallSysId,
            @RequestParam UUID userSysId,
            @RequestBody ShowRequestDto showRequestDto
    ){
        HashMap<String, String> exceptionMessage = new HashMap<>();
        try{
            return new ResponseEntity(showService.createShow(showRequestDto, hallSysId, userSysId), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
        }catch (InvalidAttributesException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
        }catch (UnAuthorizedException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/search")
    public ResponseEntity searchShows(
            @RequestParam String city,
            @RequestParam String movie
    ){
        List<Show> shows = showService.searchShows(movie, city);
        return new ResponseEntity(shows, HttpStatus.OK);
    }

    @GetMapping("/seat-status")
    public ResponseEntity getShowSeatStatus(@RequestParam UUID showId){
        return new ResponseEntity(showService.fetchSeatStatusByShowId(showId), HttpStatus.OK);
    }

    @PostMapping("/book-seat")
    public ResponseEntity bookSeat(@RequestParam UUID userId,
                                   @RequestParam UUID showId,
                                   @RequestBody BookSeatDto bookSeatDto
                                   ){

         HashMap<String, String> response = new HashMap<>();
         try{
             BookedSeat bookedSeat = showService.bookThisSeat(userId, showId, bookSeatDto);
             return new ResponseEntity(bookedSeat, HttpStatus.CREATED);
         }catch(Exception e){
              response.put("message", e.getMessage());
              return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
         }
    }


}