package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    private static Faker faker;

    @BeforeEach
    void beforeAll() {
        faker = new Faker(new Locale("ru"));
    }


    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var city = new String[]{"Салехард", "Анадырь", "Ханты-Мансийск", "Нарьян-Мар", "Биробиджан",
                "Севастополь", "Санкт-Петербург", "Москва", "Ярославль", "Ульяновск", "Тюмень", "Тула",
                "Томск", "Тверь", "Тамбов", "Смоленск", "Екатеринбург", "Южно-Сахалинск", "Саратов", "Самара",
                "Рязань", "Ростов-на-Дону", "Псков", "Пенза", "Орёл", "Оренбург", "Омск", "Новосибирск", "Великий",
                "Новгород", "Нижний Новгород", "Мурманск", "Красногорск", "Магадан", "Липецк",
                "Санкт-Петербург", "Курск", "Курган", "Кострома", "Киров", "Кемерово", "Калуга", "Калининград",
                "Иркутск", "Воронеж", "Вологда", "Волгоград", "Владимир", "Брянск", "Белгород", "Астрахань",
                "Архангельск", "Благовещенск", "Хабаровск", "Ставрополь", "Владивосток", "Пермь", "Красноярск",
                "Краснодар", "Петропавловск-Камчатский", "Чита", "Барнаул", "Чебоксары", "Грозный", "Абакан",
                "Ижевск", "Кызыл", "Казань", "Владикавказ", "Якутск", "Саранск", "Симферополь", "Сыктывкар",
                "Петрозаводск", "Черкесск", "Элиста", "Нальчик", "Махачкала", "Улан-Удэ", "Уфа", "Горно-Алтайск",
                "Майкоп", "Челябинск", "Иваново", "Йошкар-Ола", "Магас"};
        return city[new Random().nextInt(city.length)];
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }


}
