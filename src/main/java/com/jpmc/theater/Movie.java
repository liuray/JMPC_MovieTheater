package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Movie {
	private static int MOVIE_CODE_SPECIAL = 1;

	private String title;
	private String description;
	private Duration runningTime;
	private double ticketPrice;
	private int specialCode;

	public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
		this.title = title;
		this.runningTime = runningTime;
		this.ticketPrice = ticketPrice;
		this.specialCode = specialCode;
	}

	public String getTitle() {
		return title;
	}

	public Duration getRunningTime() {
		return runningTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public double calculateTicketPrice(Showing showing) {
		double price = ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getStartTime());
		return Utllity.roundUp(price);
	}

	private double getDiscount(int showSequence, LocalDateTime localDateTime) {
		double specialDiscount = 0;
		if (MOVIE_CODE_SPECIAL == specialCode) {
			specialDiscount = ticketPrice * 0.2; // 20% discount for special movie
		}

		double sequenceDiscount = 0;
		if (showSequence == 1) {
			sequenceDiscount = 3; // $3 discount for 1st show
		} else if (showSequence == 2) {
			sequenceDiscount = 2; // $2 discount for 2nd show
		}
		double dayDiscount = 0; // $1 disount for 7th day of month
		if (localDateTime.getDayOfMonth() == 7) {
			dayDiscount = 1;
		}
		double showTimeDiscount = 0;
		if ((localDateTime.getHour() >= 11 && localDateTime.getHour() <= 15)
				|| (localDateTime.getHour() == 16 && localDateTime.getMinute() == 0)) {
			showTimeDiscount = ticketPrice * 0.25;
		}

		// biggest discount wins
		return Math.max(Math.max(dayDiscount, showTimeDiscount), Math.max(specialDiscount, sequenceDiscount));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Movie movie = (Movie) o;
		return Double.compare(movie.ticketPrice, ticketPrice) == 0 && Objects.equals(title, movie.title)
				&& Objects.equals(description, movie.description) && Objects.equals(runningTime, movie.runningTime)
				&& Objects.equals(specialCode, movie.specialCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
	}
}