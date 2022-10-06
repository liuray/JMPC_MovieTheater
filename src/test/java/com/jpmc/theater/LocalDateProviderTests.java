package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
    	LocalDateProvider provider = new LocalDateProvider();
    	    	
    	assertEquals(provider.singleton().currentDate(), LocalDateProvider.singleton().currentDate());

    }
}
