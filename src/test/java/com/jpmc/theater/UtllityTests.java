package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UtllityTests {
	@Test
	public void handlePluralWith1() {
		assertEquals(Utllity.handlePlural(1), "");
	}
	@Test
	public void handlePluralwith2() {
		assertEquals(Utllity.handlePlural(2), "s");
	}
	
	@Test
	public void roundUpTest1() {
		assertEquals(Utllity.roundUp(10.895), 10.90);
	}
	
	@Test
	public void roundUpTest2() {
		assertEquals(Utllity.roundUp(10.894), 10.89);
	}
}
