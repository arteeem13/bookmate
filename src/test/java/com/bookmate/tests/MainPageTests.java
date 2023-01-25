package com.bookmate.tests;

import com.bookmate.configurations.TestBase;
import com.bookmate.dataTests.Steps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.bookmate.dataTests.Constants.BASE_URL_RU;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@DisplayName("Веб-элементы главной страницы")
@Tag("properties")
public class MainPageTests extends TestBase {
    @ValueSource(strings = {
            "Поиск",
            "Книги",
            "Аудиокниги",
            "Комиксы",
            "Полки",
            "Подарить подписку"
    })
    @ParameterizedTest(name = "Есть категория {0} в хедере главного меню")
    @DisplayName("Шапка. ")
    void displayedCategoriesInTopMenu(String searchQuery) {
        Steps.openPage(BASE_URL_RU);
        Steps.elementShouldHaveText($(".header"), "Хеддер главного меню", searchQuery);
    }

    @Test
    @DisplayName("В разделе Бестселлеры отображается 10 книг")
    void displayedEightBooksInBestseller() {
        Steps.openPage(BASE_URL_RU);
        int countBooksBestsellers = 10;
        $(byText("Бестселлеры")).scrollTo();
        for (int i = 0; i < countBooksBestsellers; i++) {
            Steps.elementIsDisplayed($(".index-layout-showcase__books").$(".book").sibling(i),
                    "10 книг в блоке бестселлеры");
        }
        Steps.elementExist($(".index-layout-showcase__books").$(".book").sibling(countBooksBestsellers),
                " 11я книга в разделе бестселлеры");
    }

    @CsvSource({
            "О Букмейте, Книги становятся ближе",
            "Библиотека, Книги",
            "Подписка, Какую подписку выбрать?",
            "Опубликовать книгу, Опубликовать книгу",
            "Контакты, Контакты",
            "Вакансии, Работа",
            "Помощь, Поддержка",
            "Правовая информация, Правовая информация",
            "Карта сайта, Карта сайта"
    })
    @ParameterizedTest(name = "Отображается заголовок {1} на странице {0} при переходе из футера")
    @DisplayName("Футер. ")
    void clickableCategoriesInFooter(String footerCategories, String header) {
        Steps.openPage(BASE_URL_RU);
        Steps.scrollToElement($(".footer"), "футера");
        Steps.clickOnElement($(".footer").$(byText(footerCategories)), footerCategories);
        Steps.elementShouldHaveText($(".heading"), "Заголовок страницы", header);
    }
}
