package com.proj.utils.etc;

import com.github.javafaker.Faker;

public final class FakerUtils {

    private static final Faker faker = new Faker();

    private FakerUtils() {
    }

    static int getNumber(int startvalue, int endvalue) {
        return faker.number().numberBetween(startvalue, endvalue);
    }

    static String getFirstName() {
        return faker.name().firstName();
    }

    static String getLastName() {
        return faker.name().lastName();
    }
}
