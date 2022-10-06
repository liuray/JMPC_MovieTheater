package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TheaterTests {

	@Test
	void totalDiscountedFeeShowing11to4() {
		Customer customer = new Customer("Henry", "123");
		Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 7,
				LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
		assertEquals(new Reservation(customer, showing, 3).totalFee(), 37.5);
	}

	@Test
	void totalDiscountedFeeShowing7th() {
		Customer customer = new Customer("John", "345");
		Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 7,
				LocalDateTime.parse("2022-10-07T23:00"));
		assertEquals(new Reservation(customer, showing, 3).afterDiscount(),34.5);
	}

	@Test
	void printMovieSchedule() {
		Theater theater = new Theater(LocalDateProvider.singleton());
		theater.printSchedule();
	}
}
