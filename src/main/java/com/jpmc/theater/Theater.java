package com.jpmc.theater;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.print.attribute.standard.PrinterURI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Theater {

	LocalDateProvider provider;
	private List<Showing> schedule;

	public Theater(LocalDateProvider provider) {
		this.provider = provider;

		Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
		Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
		Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
		schedule = List.of(new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
				new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
				new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
				new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
				new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
				new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
				new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
				new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
				new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))),
				new Showing(spiderMan, 10, LocalDateTime.parse("2022-10-07T23:00"))

		);
	}

	public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
		Showing showing;
		try {
			showing = schedule.get(sequence - 1);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
		}

		return new Reservation(customer, showing, howManyTickets);
	}

	public void printSchedule() {
		System.out.println(provider.currentDate());
		System.out.println("===================================================");
		schedule.forEach(s -> System.out.println(s.getSequenceOfTheDay() + ": " + Utllity.timeFomater(s.getStartTime())
				+ " " + s.getMovie().getTitle() + " " + Utllity.humanReadableFormat(s.getMovie().getRunningTime())
				+ " $" + s.getMovieFee()));
		System.out.println("===================================================");
	}

	public void printJson() {
		System.out.println("====================Json Format====================");

		Gson gson = GsonUtility.GSONBuilder();
		String json = gson.toJson(schedule);
		System.out.println(json);

	}

	public static void main(String[] args) {
		Theater theater = new Theater(LocalDateProvider.singleton());
		theater.printSchedule();
		theater.printJson();

	}
}
