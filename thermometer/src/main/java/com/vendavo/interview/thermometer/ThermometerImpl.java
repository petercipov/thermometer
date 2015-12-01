package com.vendavo.interview.thermometer;

public class ThermometerImpl implements Thermometer{

	public Color measure(double temperature) throws IllegalAccessException {
		if (temperature >= 40d) return Color.RED;
		if (temperature >= 35d && temperature < 40d) return Color.ORANGE;
		if (temperature >= 25d && temperature < 35d) return Color.YELLOW;
		if (temperature >= 10d && temperature < 25d) return Color.GREEN;
		if (temperature >= 5d && temperature < 10d) return Color.LIGHT_BLUE;
		if (temperature >= -10d && temperature < 5d) return Color.BLUE;
		
		return Color.DARK_BLUE;
	}

}
