package ru.netology.cardorder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @BeforeEach
    void siteOpen() {
        open("http://localhost:9999");
    }

    @Test
    void shouldGetSuccessMessageAfterCorrectFilling() {
        Configuration.holdBrowserOpen = true;
        SelenideElement form = $("[enctype='application/x-www-form-urlencoded']");
        form.$("[name='name']").setValue("Анна-Мария Иванова-Редгрейв");
        form.$("[name='phone']").setValue("+89991234567");
        form.$(".checkbox__box").click();
        //Selenide.sleep(10000);
        form.$(".button").click();
        //Selenide.sleep(1000);
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldGetWarningForIncorrectName() {
        //Configuration.holdBrowserOpen = true;
        SelenideElement form = $("[enctype='application/x-www-form-urlencoded']");
        form.$("[name='name']").setValue("Иван11");
        form.$("[name='phone']").setValue("+89991234567");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        form.$("[data-test-id=\"name\"].input_invalid span span.input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetWarningForIncorrectPhone() {
        //Configuration.holdBrowserOpen = true;
        SelenideElement form = $("[enctype='application/x-www-form-urlencoded']");
        form.$("[name='name']").setValue("Иван Иванов");
        form.$("[name='phone']").setValue("08999123456");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        form.$("[data-test-id=\"phone\"].input_invalid span span.input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldGetRedTextIfCheckboxIsntSelect() {
        //Configuration.holdBrowserOpen = true;
        SelenideElement form = $("[enctype='application/x-www-form-urlencoded']");
        form.$("[name='name']").setValue("Анна-Мария Иванова-Редгрейв");
        form.$("[name='phone']").setValue("+89991234567");
        //form.$(".checkbox__box").click();
        //Selenide.sleep(10000);
        form.$(".button").click();
        $(".checkbox.input_invalid").shouldBe(visible);

    }

    @Test
    void shouldGetWarningIfNameIsntFilling() {
        //Configuration.holdBrowserOpen = true;
        SelenideElement form = $("[enctype='application/x-www-form-urlencoded']");
        //form.$("[name='name']").setValue("Иван Иванов");
        form.$("[name='phone']").setValue("+89991234567");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        form.$("[data-test-id=\"name\"].input_invalid span span.input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetWarningIfPhoneIsntFilling() {
        //Configuration.holdBrowserOpen = true;
        SelenideElement form = $("[enctype='application/x-www-form-urlencoded']");
        form.$("[name='name']").setValue("Иван Иванов");
        //form.$("[name='phone']").setValue("08999123456");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        form.$("[data-test-id=\"phone\"].input_invalid span span.input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

}
