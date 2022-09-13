package ru.netology.cardorder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
    @Test
    void shouldGetSuccessMessageAfterCorrectFilling() {
        open("http://localhost:9999");
        SelenideElement form = $("[enctype='application/x-www-form-urlencoded']");
        form.$("[name='name']").setValue("Иван Иванов");
        form.$("[name='phone']").setValue("+89991234567");
        //form.$(".checkbox__control").click();
        form.$(".checkbox__box").click();
        form.$(".button").click();
        Selenide.sleep(1000);
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        //Thread.sleep(10000);
        //Selenide.sleep(10000);
    }
}
