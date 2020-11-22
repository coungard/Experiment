package ru;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5};
	}

//	private static BigInteger fib2(long i) {
//		if (i == 0) return BigInteger.ZERO;
//		if (i == 1) return BigInteger.ONE;
//
//		return cache.computeIfAbsent(i, n -> fib2(n - 2).add(fib2(n - 1)));
//	}

	public static int fib(int n) {
		if (n <= 1) return n;
		else return fib(n - 1) + fib(n - 2);
	}


	private static boolean isSimple(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) return false;
		}
		return true;
	}


	class Solution {
		public int[] shuffle(int[] nums, int n) {
			return new int[]{};
		}
	}

	public static boolean isPrime(int num) {
		if (num < 2) return false;
		return IntStream.range(2, num)
				.noneMatch(n -> (num) % n == 0);
	}

	// input  -> 4
	// output -> false

	public static int solution(int number) {
		return IntStream.range(0, number)
				.filter(n -> n % 3 == 0 || n % 5 == 0)
				.sum();
	}

	public static int sortDesc(final int num) {
		String[] array = String.valueOf(num).split("");
		Arrays.sort(array, Collections.reverseOrder());
		return Integer.parseInt(String.join("", array));
	}

	private static void print(List<? super Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
		}
	}

	/**
	 * filter by User.age >= provided age
	 * map to String like firstName + lastName
	 * order by alphabet
	 * return List of String
	 *
	 * @param users
	 * @param age
	 * @return
	 */
	public static List<String> getUserDetail(List<User> users, int age) {
		return users.stream()
				.filter((User u) -> u.age >= age)
				.map((User u) -> u.firstName + " " + u.lastName)
				.sorted()
				.collect(Collectors.toList());
	}
}

class User {
	String firstName;
	String lastName;
	int age;

	public User(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	void test() {
		try {
			System.out.println("ehlekj");
		} catch (OutOfMemoryError error) {
			error.printStackTrace();
		}
		Exception exception;
	}
}
