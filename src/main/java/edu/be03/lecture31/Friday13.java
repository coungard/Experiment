package edu.be03.lecture31;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Friday13 {
	public static void main(String[] args) {
		int year = getJasonVoorheesDay(1992, 2020);
		System.out.println("The most bloodiest year = " + year);
	}

	public static int getJasonVoorheesDay(int yearFrom, int yearTo) {
		int result = yearFrom;
		int previousFridayCount = 0;

		for (int i = yearFrom; i <= yearTo; i++) {
			LocalDate currentDate = LocalDate.of(i, Month.JANUARY, 13);
			int friday13Count = 0;

			for (int j = 0; j < 12; j++) {
				if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
					friday13Count++;
				}
				currentDate = currentDate.plusMonths(1);
			}

			if (friday13Count > previousFridayCount) {
				result = i;
				previousFridayCount = friday13Count;
			}
		}
		return result;
	}
}
