package tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.MainPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@Execution(ExecutionMode.CONCURRENT)
public class TitleTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    void initializeInstances() {
        mainPage = new MainPage(driver);
        log.debug("Initialize pages instances.");
    }

    @ParameterizedTest
    @Tag("regression")
    @CsvFileSource(resources = "/testdata.csv")
    void shouldValidateCorrectTitle(String url, String expectedTitle) {
        //GIVEN
        mainPage.goToPage(url);
        //WHEN
        String actualTitle = mainPage.getTitle();
        //THEN
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @ParameterizedTest
    @CsvSource(value = {"http://sii.pl/; Kontakt | Sii Polska"}, delimiter = ';')
    void shouldValidateChangingPage(String url, String expectedTitle) {
        log.debug("Executing test: {}", new Object(){}.getClass().getEnclosingMethod().getName());
        //GIVEN
        mainPage.goToPage(url);
        mainPage.clickContactButton();
        //WHEN
        String actualTitle = mainPage.getTitle();
        //THEN
        log.info("Expected title: {}", expectedTitle);
        log.info("Actual title: {}", actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @ParameterizedTest
    @CsvSource(value = {"http://sii.pl/; Kontakt | Sii Polska"}, delimiter = ';')
    void lala(String url) {
        log.debug("Executing test: {}", new Object(){}.getClass().getEnclosingMethod().getName());
        // GIVEN
        mainPage.goToPage(url);
        // WHEN
        mainPage.clickWhoAreWeButton();
        // THEN
        assertThat(mainPage.checkIfMainMenuIsExpanded()).isEqualTo(true);
    }
}
