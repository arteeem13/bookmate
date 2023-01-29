package com.andreev.tests;

import com.andreev.pages.StepsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.andreev.data.Constants.BASE_URL_RU;
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
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.elementShouldHaveText($(".header"), "Хеддер главного меню", searchQuery);
    }

    @Test
    @DisplayName("В разделе Бестселлеры отображается 10 книг")
    void displayedEightBooksInBestseller() {
        StepsPage.openPage(BASE_URL_RU);
        int countBooksBestsellers = 10;
        $(byText("Бестселлеры")).scrollTo();
        for (int i = 0; i < countBooksBestsellers; i++) {
            StepsPage.elementIsDisplayed($(".index-layout-showcase__books").$(".book").sibling(i),
                    "10 книг в блоке бестселлеры");
        }
        StepsPage.elementExist($(".index-layout-showcase__books").$(".book").sibling(countBooksBestsellers),
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
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.scrollToElement($(".footer"), "футера");
        StepsPage.clickOnElement($(".footer").$(byText(footerCategories)), footerCategories);
        StepsPage.elementShouldHaveText($(".heading"), "Заголовок страницы", header);
    }
}
