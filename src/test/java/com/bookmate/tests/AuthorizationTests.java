package com.bookmate.tests;

import com.bookmate.configurations.AuthConfig;
import com.bookmate.configurations.TestBase;
import com.bookmate.dataTests.Steps;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.bookmate.dataTests.Constants.BASE_URL_RU;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@DisplayName("Проверка авторизации")
@Tag("ui_tests")
public class AuthorizationTests extends TestBase {
    private static final AuthConfig credentials = ConfigFactory.create(AuthConfig.class);

    @DisplayName("Успешная авторизация при вводе валидных email и password")
    @Test
    void successfulAuthorization() {
        String email = credentials.validEmail();
        String password = credentials.validPassword();
        String[] splitEmail = email.split("@", 2);
        String login = splitEmail[0];

        Steps.openPage(BASE_URL_RU);
        Steps.clickOnElement($(".login-button"), "кнопке Войти");
        Steps.clickOnElement($(".auth__subaction").$(byText("Почта")), "кнопке Почта");
        Steps.setValueInField($("[name=email]"), "email", email);
        Steps.setValueInField($("[name=password]"), "пароль", password);
        sleep(2000);
        Steps.clickOnElement($(byText("Войти")), "кнопке Войти");
        Steps.elementContainsText($(".user-avatar").lastChild(),
                "Селектор хеддера user-avatar", login);
    }

    @DisplayName("Неактивная кнопка Войти при вводе валидного email и неверного password")
    @Test
    void unsuccessfulAuthorization() {
        String email = credentials.validEmail();
        String password = credentials.validPassword();
        String incorrectPassword = "123aa";

        Steps.openPage(BASE_URL_RU);
        Steps.clickOnElement($(".login-button"), "кнопке Войти");
        Steps.clickOnElement($(".auth__subaction").$(byText("Почта")), "кнопке Почта");
        Steps.setValueInField($("[name=email]"), "email", email);
        Steps.setValueInField($("[name=password]"), "пароль (валидный)", password);
        sleep(2000);
        Steps.setValueInField($("[name=password]"), "пароль (неверный)", incorrectPassword);
        Steps.elementIsDisabled($(byText("Войти")), "кнопка Войти");
    }
}
