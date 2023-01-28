package com.andreev.tests;

import com.andreev.data.Steps;
import com.andreev.helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.andreev.data.Constants.BASE_URL_RU;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка вкладки console log")
@Tag("properties")
public class LoggerTest extends TestBase {

    @Test
    @DisplayName("Страница console log не содержит ошибок")
    void consoleShouldNotHaveErrorsTest() {
        Steps.openPage(BASE_URL_RU);
        step("Проверка наличия ошибок в консоли с текстом 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";
            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}
