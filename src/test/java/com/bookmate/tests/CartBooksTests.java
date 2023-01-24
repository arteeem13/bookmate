package com.bookmate.tests;

import com.bookmate.configurations.TestBase;
import com.bookmate.dataTests.Steps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.bookmate.dataTests.Constants.BASE_URL_RU;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@DisplayName("Карточка книги")
@Tag("properties")
public class CartBooksTests extends TestBase {
    @Test
    @DisplayName("Есть активная кнопка Читать")
    void bottomRead() {
        Steps.openPage(BASE_URL_RU);
        Steps.clickOnElement($(".header").$(byText("Книги")), "Книги");
        Steps.clickOnElement($(".book__description"), "первой книге на странице");
        sleep(2000);
        Steps.elementShouldHaveText($("#read-button"),
                "Есть кнопка, которая", "Читать");
        Steps.elementIsEnable($("#read-button"), "Читать");
    }

    @Test
    @DisplayName("Есть активная кнопка Поделиться")
    void bottomShare() {
        Steps.openPage(BASE_URL_RU);
        Steps.clickOnElement($(".header").$(byText("Книги")), "Книги");
        Steps.clickOnElement($(".book__description"), "первой книге на странице");
        sleep(2000);
        Steps.elementIsEnable($(".share-button__text"), "Поделиться");
    }

    @ValueSource(strings = {
            "О книге",
            "Впечатления",
            "Цитаты",
            "Читают",
            "На полках",
            "Похожие книги"
    })
    @ParameterizedTest(name = "Есть дополнительный раздел {0}")
    @DisplayName("Доп. элемент. ")
    void displayedCategoriesInTopMenu(String searchQuery) {
        Steps.openPage(BASE_URL_RU);
        Steps.clickOnElement($(".header").$(byText("Книги")), "Книги");
        Steps.clickOnElement($(".book__description"), "первой книге на странице");
        sleep(2000);
        Steps.elementShouldHaveText($(".resource-tabs"),
                "Дополнительный элемент карточки", searchQuery);
    }
}
