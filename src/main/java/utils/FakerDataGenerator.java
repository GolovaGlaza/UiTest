package utils;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

    private Faker faker;

    public FakerDataGenerator() {
        faker = new Faker();
    }

    public String generateUsername() {
        return faker.name().username();
    }

    public String generatePassword() {
        return faker.internet().password();
    }

    public String generateFullName() {
        return faker.name().fullName();
    }

    public String generateCountry() {
        return faker.address().country();
    }

    public String generateCity() {
        return faker.address().city();
    }

    public String generateCreditCard() {
        return faker.finance().creditCard();
    }

    public String gerateMonth(){
    }
}

