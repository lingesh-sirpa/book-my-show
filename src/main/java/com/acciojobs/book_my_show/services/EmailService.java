package com.acciojobs.book_my_show.services;

import com.acciojobs.book_my_show.models.BookedSeat;
import com.acciojobs.book_my_show.models.Show;
import com.acciojobs.book_my_show.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private TemplateEngine templateEngine;
    private NotificationService notificationService;

    @Autowired
    public EmailService(TemplateEngine templateEngine,
                        NotificationService notificationService){
        this.templateEngine = templateEngine;
        this.notificationService = notificationService;
    }

    public void sendBookingConfirmationEmail(
            User user,
            Show show,
            BookedSeat bookedSeat
    ) {

        Context context = new Context();

        // Customer Information
        context.setVariable("customerName", user.getFullName());
        context.setVariable("customerEmail", user.getEmail());

        // Booking Information
        context.setVariable("bookingId", bookedSeat.getBookingId());
        context.setVariable("seatId", bookedSeat.getSeatId());

        // Movie / Show Information
        context.setVariable("movieName", show.getMovieName());
        context.setVariable("showPrice", show.getShowPrice());
        context.setVariable("startTime", show.getStartTime());
        context.setVariable("endTime", show.getEndTime());

        // Generate HTML content from Thymeleaf template
        String htmlContent =
                templateEngine.process("book-seat", context);

        // Send email to customer
        notificationService.sendEmailNotification(
                htmlContent,
                user.getEmail(),
                "Ticket Booking Confirmation - " + show.getMovieName()
        );
    }

}
