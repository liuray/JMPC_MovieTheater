package com.jpmc.theater;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {
	
	LocalDateProvider provider = new LocalDateProvider();
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
	Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
	List<Showing> schedule = List.of(
			new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
			new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
			new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
			new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
			new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
			new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
			new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
			new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
			new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));
	
	@Test
	public void totalFeeForCustomer() {
		Theater theater = new Theater(LocalDateProvider.singleton());
		Customer john = new Customer("John Doe", "id-12345");
		Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
		assertEquals(reservation.totalFee(), 50);
	}

	@Test
	public void totalFeeForCustomer1() {

		Customer john = new Customer("john", "29");
		Reservation reserve = new Reservation(john, schedule.get(0), 20);
		assertEquals(reserve.totalFee(), 220.0);
	}

	@Test
	public void totalFeeForCustomer1WithDiscount() {
		Customer john = new Customer("ray", "18");
		Reservation reserve = new Reservation(john, schedule.get(0), 20);
		assertEquals(reserve.afterDiscount(), 160.0);
	}
    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);
    }
}
