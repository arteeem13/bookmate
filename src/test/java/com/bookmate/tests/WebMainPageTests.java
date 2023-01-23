package com.bookmate.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        open(BASE_URL);
        $(".header").shouldHave(Condition.text(searchQuery));
    }

    @DisplayName("В блоке Бестселлеры 10 книг")
    @Tag("properties")
    @Test
    void displayedEightBooksInBestseller() {
        open(BASE_URL);
        int countBooksBestsellers = 10;
        $(byText("Бестселлеры")).scrollTo();
        for (int i = 0; i < countBooksBestsellers; i++) {
            $(".index-layout-showcase__books").$(".book").sibling(i).isDisplayed();
        }
        $(".index-layout-showcase__books").$(".book").sibling(countBooksBestsellers).exists();
    }

    @DisplayName("Проверка категорий в главном меню на главной странице")
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
        open(BASE_URL);
        $(".footer").scrollTo();
        $(".footer").$(byText(footerCategories)).click();
        $(".heading").shouldHave(Condition.text(header));
    }
}
