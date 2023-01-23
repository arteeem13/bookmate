package ru.marketYandex.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.marketYandex.configurations.TestBase;
import ru.marketYandex.pageObjects.CategoriesInTopMenu;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebMainPageTests extends TestBase {
    private final String URL_MAIN_PAGE = "https://market.yandex.ru/";

    @DisplayName("Проверка категорий в главном меню на главной странице")
    @EnumSource(CategoriesInTopMenu.class)
    @ParameterizedTest(name = "Есть категория {0} в главном меню")
    void displayedCategoriesInTopMenu(CategoriesInTopMenu categoriesInTopMenu) {
        open(URL_MAIN_PAGE);
        if ($(byText("Подтвердите, что запросы отправляли вы, а не робот")).isDisplayed()) {
            $(".CheckboxCaptcha-Button").click();
        }

        $("[data-baobab-name=top_menu]").shouldHave(Condition.text(categoriesInTopMenu.getDesc()));
    }
}
