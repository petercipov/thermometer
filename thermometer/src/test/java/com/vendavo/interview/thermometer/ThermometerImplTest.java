package com.vendavo.interview.thermometer;

import java.util.Random;
import java.util.stream.DoubleStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author pcipov
 */
public class ThermometerImplTest {
	
	private ThermometerImpl thermometer;
	private Random random;
	
	@Before
	public void before() {
		thermometer = new ThermometerImpl();
		random = new Random();
	}

	@Test
	public void overLimitOf40ThermometerMeasuresRed() throws IllegalAccessException {
		final double inclusiveLimit = 40d;
		assertEquals(Color.RED, thermometer.measure(Double.POSITIVE_INFINITY));
		
		assertHandlesRandomTemeratureSeries(inclusiveLimit, Double.POSITIVE_INFINITY, Color.RED);
		
		assertEquals(Color.RED, thermometer.measure(inclusiveLimit));
	}
	
	@Test
	public void between35IncAnd40ExcLimitThemoteherMeasuresOrange() throws IllegalAccessException {
		final double exclusiveLimit = 40d;
		final double inclusiveLimit = 35d;
		
		assertEquals(Color.ORANGE, thermometer.measure(inclusiveLimit));

		assertHandlesRandomTemeratureSeries(inclusiveLimit, exclusiveLimit, Color.ORANGE);
		
		assertEquals(Color.ORANGE, thermometer.measure(Math.nextDown(exclusiveLimit)));
		assertNotEquals(Color.ORANGE, thermometer.measure(exclusiveLimit));
	}
	
	@Test
	public void between25IncAnd35ExcLimitThemoteherMeasuresYellow() throws IllegalAccessException {
		final double exclusiveLimit = 35d;
		final double inclusiveLimit = 25d;
		
		assertEquals(Color.YELLOW, thermometer.measure(inclusiveLimit));

		assertHandlesRandomTemeratureSeries(inclusiveLimit, exclusiveLimit, Color.YELLOW);
		
		assertEquals(Color.YELLOW, thermometer.measure(Math.nextDown(exclusiveLimit)));
		assertNotEquals(Color.YELLOW, thermometer.measure(exclusiveLimit));
	}
	
	@Test
	public void between10IncAnd25ExcLimitThemoteherMeasuresGreen() throws IllegalAccessException {
		final double exclusiveLimit = 25d;
		final double inclusiveLimit = 10d;
		
		assertEquals(Color.GREEN, thermometer.measure(inclusiveLimit));

		assertHandlesRandomTemeratureSeries(inclusiveLimit, exclusiveLimit, Color.GREEN);
		
		assertEquals(Color.GREEN, thermometer.measure(Math.nextDown(exclusiveLimit)));
		assertNotEquals(Color.GREEN, thermometer.measure(exclusiveLimit));
	}
	
	@Test
	public void between5IncAnd10ExcLimitThemoteherMeasuresLightBlue() throws IllegalAccessException {
		final double exclusiveLimit = 10d;
		final double inclusiveLimit = 5d;
		
		assertEquals(Color.LIGHT_BLUE, thermometer.measure(inclusiveLimit));

		assertHandlesRandomTemeratureSeries(inclusiveLimit, exclusiveLimit, Color.LIGHT_BLUE);
		
		assertEquals(Color.LIGHT_BLUE, thermometer.measure(Math.nextDown(exclusiveLimit)));
		assertNotEquals(Color.LIGHT_BLUE, thermometer.measure(exclusiveLimit));
	}
	
	@Test
	public void betweenMinus10IncAnd5ExcLimitThemoteherMeasuresBlue() throws IllegalAccessException {
		final double exclusiveLimit = 5d;
		final double inclusiveLimit = -10d;
		
		assertEquals(Color.BLUE, thermometer.measure(inclusiveLimit));

		assertHandlesRandomTemeratureSeries(inclusiveLimit, exclusiveLimit, Color.BLUE);
		
		assertEquals(Color.BLUE, thermometer.measure(Math.nextDown(exclusiveLimit)));
		assertNotEquals(Color.BLUE, thermometer.measure(exclusiveLimit));
	}
	
	@Test
	public void belowMinus10LimitThermometherMeasuresBarkBlue() throws IllegalAccessException {
		final double exclusiveLimit = -10d;
		
		assertNotEquals(Color.DARK_BLUE, thermometer.measure(exclusiveLimit));
		assertEquals(Color.DARK_BLUE, thermometer.measure(Math.nextDown(exclusiveLimit)));
		
		assertHandlesRandomTemeratureSeries(Double.NEGATIVE_INFINITY, exclusiveLimit, Color.DARK_BLUE);
		
		assertEquals(Color.DARK_BLUE, thermometer.measure(Double.NEGATIVE_INFINITY));
	}
	
	private void assertHandlesRandomTemeratureSeries(double inclusiveLimit, double exclusiveLimit, Color color) {
		DoubleStream doubles = random.doubles(inclusiveLimit, exclusiveLimit);
		doubles.limit(10000).forEach((double temperature) -> {
			try {
				assertEquals("Failure for " +temperature, color, thermometer.measure(temperature));
			} catch(Exception ex) {
				throw new IllegalStateException(ex);
			}
		});
	}
}
