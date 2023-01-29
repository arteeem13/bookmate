package com.andreev.tests;

import com.andreev.configurations.AuthConfig;
import com.andreev.pages.StepsPage;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.andreev.data.Constants.BASE_URL_RU;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@DisplayName("Авторизация")
@Tag("properties")
public class AuthorizationTests extends TestBase {
    private static final AuthConfig credentials = ConfigFactory.create(AuthConfig.class);

    private final String email = credentials.validEmail();
    private final String password = credentials.validPassword();
    private final String[] splitEmail = email.split("@", 2);
    private final String login = splitEmail[0];

    @DisplayName("Успешная авторизация при вводе валидных email и password")
    @Test
    void successfulAuthorization() {
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".login-button"), "кнопке Войти");
        StepsPage.clickOnElement($(".auth__subaction").$(byText("Почта")), "кнопке Почта");
        StepsPage.setValueInField($("[name=email]"), "email", email);
        StepsPage.setValueInField($("[name=password]"), "пароль", password);
        sleep(5000);
        StepsPage.clickOnElement($(byText("Войти")), "кнопке Войти");
        StepsPage.elementContainsText($(".user-avatar").lastChild(),
                "Селектор хеддера user-avatar", login);
    }

    @DisplayName("Неактивная кнопка Войти при вводе валидного email и неверного password")
    @Test
    void unsuccessfulAuthorization() {
        String incorrectPassword = "123aa";

        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".login-button"), "кнопке Войти");
        StepsPage.clickOnElement($(".auth__subaction").$(byText("Почта")), "кнопке Почта");
        StepsPage.setValueInField($("[name=email]"), "email", email);
        StepsPage.setValueInField($("[name=password]"), "пароль (валидный)", password);
        sleep(2000);
        StepsPage.setValueInField($("[name=password]"), "пароль (неверный)", incorrectPassword);
        StepsPage.elementIsDisabled($(byText("Войти")), "кнопка Войти");
    }
}
