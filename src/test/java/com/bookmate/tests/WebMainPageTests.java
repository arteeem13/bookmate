package com.bookmate.tests;

import com.bookmate.helpers.Steps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@DisplayName("Проверка веб-элементов главной страницы")
public class WebMainPageTests {
    private final String BASE_URL = "https://bookmate.com/";

    @DisplayName("Проверка категорий в главном меню на главной странице")
    @Tag("properties")
    @ValueSource(strings = {
            "Поиск",
            "Книги",
            "Аудиокниги",
            "Комиксы",
            "Полки",
            "Подарить подписку"
    })
    @ParameterizedTest(name = "Есть категория {0} в хедере главного меню")
    void displayedCategoriesInTopMenu(String searchQuery) {
        Steps.openPage(BASE_URL);
        Steps.elementShouldHaveText($(".header"), "Хеддер главного меню", searchQuery);
    }

    @DisplayName("В блоке Бестселлеры 10 книг")
    @Tag("properties")
    @Test
    void displayedEightBooksInBestseller() {
        Steps.openPage(BASE_URL);
        int countBooksBestsellers = 10;
        $(byText("Бестселлеры")).scrollTo();
        for (int i = 0; i < countBooksBestsellers; i++) {
            Steps.elementIsDisplayed($(".index-layout-showcase__books").$(".book").sibling(i),
                    "10 книг в разделе бестселлеры");
        }
        Steps.elementExist($(".index-layout-showcase__books").$(".book").sibling(countBooksBestsellers),
                " 11я книга в разделе бестселлеры");
    }

    @DisplayName("Проверка заголовков страниц в футере")
    @Tag("properties")
    @CsvSource({
            "О Букмейте, Книги становятся ближе",
            "Библиотека, Книги",
            "Подписка, Какую подписку выбрать?",
            "Опубликовать книгу, Опубликовать книгу",
            "Контакты, Контакты",
            "Вакансии, Работа",
            "Помощь, Поддержка",
            "Правовая информация, Правовая информация",
            "Карта сайта, Карта сайта",
    })
    @ParameterizedTest(name = "Отображается заголовок {1} на странице {0} при переходе из футера")
    void clickableCategoriesInFooter(String footerCategories, String header) {
        Steps.openPage(BASE_URL);
        Steps.scrollToElement($(".footer"), "футера");
        Steps.clickOnElement($(".footer").$(byText(footerCategories)), footerCategories);
        Steps.elementShouldHaveText($(".heading"), "Заголовок страницы",header);
    }
}
