package com.prestashop.tests;

import java.util.ArrayList;

import com.github.javafaker.Faker;

public class Fake {

	String firstName, lastname, email, number, address, city, state, country, mobilePhone, alias, pass, zip;

	Faker faker = new Faker();

	public ArrayList faker() {
		ArrayList<String> str = new ArrayList<>();
		str.add(email = faker.internet().emailAddress());
		str.add(pass = faker.internet().password(8, 12, true));
		str.add(firstName = faker.name().firstName());
		str.add(lastname = faker.name().lastName());
		str.add(address = faker.address().streetAddress());
		str.add(city = faker.address().city());
		str.add(state = faker.address().state());
		str.add(country = faker.address().country());
		str.add(mobilePhone = faker.phoneNumber().cellPhone());
		str.add(alias = faker.name().username());
		str.add(String.valueOf(number(10000, 99999)));
		return str;
	}

	public int number(int a, int b) {

		return faker.random().nextInt(a, b);

	}

}
