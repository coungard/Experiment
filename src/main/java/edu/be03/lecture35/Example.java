package edu.be03.lecture35;

import java.io.ByteArrayInputStream;

public class Example {
	public static void main(String[] args) {
		String text = "Люблю грозу в начале мая";

		ByteArrayInputStream inputStream = new ByteArrayInputStream(text.getBytes());
		byte[] bytes = inputStream.readAllBytes();
		System.out.println(new String(bytes));
	}
}
