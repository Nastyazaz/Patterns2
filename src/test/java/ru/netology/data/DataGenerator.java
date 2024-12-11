package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void setUpAll(Registrationinfo info) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new Registrationinfo(info.getLogin(), info.getPassword(), info.getStatus())) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    public static Registrationinfo generateUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        setUpAll(new Registrationinfo(login, password, "active"));
        return new Registrationinfo(login, password, "active");
    }

    public static Registrationinfo generateUserNoAuth(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        return new Registrationinfo(login, password, "active");
    }

    public static Registrationinfo generateBlockedUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        setUpAll(new Registrationinfo(login, password, "blocked"));
        return new Registrationinfo(login, password, "blocked");
    }

    public static Registrationinfo generateInvalidLoginUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        setUpAll(new Registrationinfo(login, password, "active"));
        return new Registrationinfo(faker.name().username(), password, "active");
    }

    public static Registrationinfo generateInvalidPasswordUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        setUpAll(new Registrationinfo(login, password, "active"));
        return new Registrationinfo(login, faker.internet().password(), "active");
    }

}





