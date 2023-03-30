import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests {

    @Test
    void successLoginTest() {
        Configuration.holdBrowserOpen = true; // чтобы не закрывался браузер
//        Configuration.browser = "firefox";
//        Configuration.browserSize = "100x100";

        //Открыть форму авторизации
        open("https://qa.guru/cms/system/login");
        //Ввести адрес электронной почты
        $("[name=email]").setValue("qagurubot@gmail.com");
        //Ввести пароль
        $("[name=password]").setValue("qagurupassword");
        //Нажать кнопку "Войти"
        $(".btn-success").click();
        //Нажать на кнопку "Личный кабинет"
        $(".main-header__login").click();
        //Проверить успешную авторизацию
        $(".logined-form").shouldHave(text("Здравствуйте, QA_GURU_BOT"));
    }

    @Test
    void negativeLoginTest() {
        Configuration.holdBrowserOpen = true; // чтобы не закрывался браузер
        open("https://qa.guru/cms/system/login");
        $("[name=email]").setValue("qagurubot@gmail.com");
        $("[name=password]").setValue("test");
        $(".btn-success").click();
        $(".btn-success").shouldHave(text("Неверный пароль"));
    }
}
