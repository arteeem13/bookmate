package com.andreev.tests;

import com.andreev.pages.StepsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.andreev.data.Constants.BASE_URL_RU;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@DisplayName("Поиск")
@Tag("properties")
public class SearchTests extends TestBase {
    @CsvSource({
            "Яков Миркин, Яков Миркин",
            "Яков, Яков Перельман",
            "Миркин, Владимир Миркин",
    })
    @ParameterizedTest(name = "На странице есть имя {1} при вводе в строке поиска {0}")
    @DisplayName("По автору. ")
    void searchOnAuthor(String setValue, String searchValue) {
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".header").$(byText("Поиск")), "Поиск");
        StepsPage.setValueInField($("#search-input"), "Поиск", setValue);
        sleep(2000);
        StepsPage.elementShouldHaveText($(".author"), "Раздел авторы", searchValue);
    }

    @CsvSource({
            "Думай и богатей, Думай и богатей",
            "Думай и, Думай и богатей",
            "и богатей, Думай и богатей",
    })
    @ParameterizedTest(name = "На странице есть имя {1} при вводе в строке поиска {0}")
    @DisplayName("По книге. ")
    void searchOnBook(String setValue, String searchValue) {
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".header").$(byText("Поиск")), "Поиск");
        StepsPage.setValueInField($("#search-input"), "Поиск", setValue);
        sleep(2000);
        StepsPage.elementShouldHaveText($(".search-best-match"), "Раздел похожее", searchValue);
    }

    @Test
    @DisplayName("В разделе Аудиокниги есть Английский язык при вводе Образование и учебные пособия")
    void searchOnShelf() {
        StepsPage.openPage(BASE_URL_RU);
        StepsPage.clickOnElement($(".header").$(byText("Поиск")), "Поиск");
        StepsPage.setValueInField($("#search-input"), "Поиск", "Образование и учебные пособия");
        sleep(2000);
        StepsPage.elementShouldHaveText($(".audiobook"),
                "Раздел Аудиокниги", "Английский язык");
    }
}
