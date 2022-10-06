package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
	
    @Test
    void specialMovieWith50PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }
    @Test
    public void DiscountWith7thDayOfMonth() {
    	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 11, LocalDateTime.parse("2022-10-07T23:00"));
        assertEquals(8, theBatMan.calculateTicketPrice(showing));
    }
    
    @Test
    public void MovieStatedAt12amWith25PercentDiscount() {
    	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 10, 0);
        Showing showing = new Showing(theBatMan, 11, LocalDateTime.parse("2022-10-01T12:00"));
        assertEquals(7.5, theBatMan.calculateTicketPrice(showing));
    }
    //$3 discount for the movie showing 1st of the day
    @Test
    public void DiscountWith3() {
    	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 10, 0);
        Showing showing = new Showing(theBatMan, 1, LocalDateTime.parse("2022-10-01T20:00"));
        assertEquals(7, theBatMan.calculateTicketPrice(showing));
    }
  //$2 discount for the movie showing 2nd of the day
    @Test
    public void DiscountWith2() {
    	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 10, 0);
        Showing showing = new Showing(theBatMan, 2, LocalDateTime.parse("2022-10-01T20:00"));
        assertEquals(8, theBatMan.calculateTicketPrice(showing));
    }
}
