package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".sii-o-nav-bar__item .sii-a-button")
    WebElement contactButton;

    @FindBy(css = "[data-menu='20383']")
    WebElement whoAreWeButton;

    @FindBy(css = "#js-main-menu-20383")
    WebElement mainMenu;

    public void goToPage(String url) {
        log.debug("Opening page.");
        log.info("Page url: {}", url);
        driver.get(url);
        driver.manage().window().maximize();
    }

    public String getTitle() {
        log.debug("Getting page title.");
        return driver.getTitle();
    }

    public void clickContactButton() {
        log.debug("Clicking contact button.");
        contactButton.click();
    }

    public void clickWhoAreWeButton() {
        log.debug("Clicking 'Who we are?' button.");
        whoAreWeButton.click();
    }

    public boolean checkIfMainMenuIsExpanded() {
        String displayValue = mainMenu.getCssValue("display");
        return displayValue.equals("flex");
    }
}
