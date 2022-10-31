package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.delivery.data.DataGenerator.*;


class DeliveryTest {


    @BeforeEach

    void setup() {
        open("http://localhost:9999");
    }
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 3;
        var firstMeetingDate = generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(generateCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(generateName("ru"));
        $("[data-test-id=phone] input").setValue(generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $$("button").findBy(Condition.exactText("Запланировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(secondMeetingDate);
        $$("button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=replan-notification]")
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id=replan-notification] button").click();
        $("[data-test-id=success-notification]")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));

    }
}
