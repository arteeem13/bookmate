package com.andreev.tests;

import com.andreev.pages.StepsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.andreev.data.Constants.BASE_URL_RU;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@DisplayName("Карточка книги")
@Tag("properties")
public class CartBooksTests extends TestBase {
    @Test
    @DisplayName("Есть активная кнопка Читать")
    void bottomRead() {
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".header").$(byText("Книги")), "Книги");
        StepsPage.clickOnElement($(".book__description"), "первой книге на странице");
        sleep(2000);
        StepsPage.elementShouldHaveText($("#read-button"),
                "Есть кнопка, которая", "Читать");
        StepsPage.elementIsEnable($("#read-button"), "Читать");
    }

    @Test
    @DisplayName("Есть активная кнопка Поделиться")
    void bottomShare() {
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".header").$(byText("Книги")), "Книги");
        StepsPage.clickOnElement($(".book__description"), "первой книге на странице");
        sleep(2000);
        StepsPage.elementIsEnable($(".share-button__text"), "Поделиться");
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
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".header").$(byText("Книги")), "Книги");
        StepsPage.clickOnElement($(".book__description"), "первой книге на странице");
        sleep(2000);
        StepsPage.elementShouldHaveText($(".resource-tabs"),
                "Дополнительный элемент карточки", searchQuery);
    }
}
