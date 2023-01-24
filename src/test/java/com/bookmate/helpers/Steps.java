package com.bookmate.helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class Steps {
     public static void openPage(String baseURL) {
        step("Открываем страницу " + baseURL, () -> {
            open(baseURL);
        });
    }

    public static void elementShouldHaveText(SelenideElement selectorElement, String nameElement, String shouldHaveText) {
        step(nameElement+ " содержит текст " + shouldHaveText, () -> {
            selectorElement.shouldHave(Condition.text(shouldHaveText));
        });
    }
    public static void elementIsDisplayed(SelenideElement selectorElement, String nameElement) {
        step("Отображается " + nameElement, () -> {
            selectorElement.isDisplayed();
        });
    }
    public static void elementExist(SelenideElement selectorElement, String nameElement) {
        step("Oтсутствует " + nameElement, () -> {
            selectorElement.exists();
        });
    }
    public static void scrollToElement(SelenideElement selectorElement, String nameElement) {
        step("Скроллим до " + nameElement, () -> {
            selectorElement.scrollTo();
        });
    }
    public static void clickOnElement(SelenideElement selectorElement, String nameElement) {
        step("Кликаем по " + nameElement, () -> {
            selectorElement.click();
        });
    }
}
