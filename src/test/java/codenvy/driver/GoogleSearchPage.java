package codenvy.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {

    private WebElement q;
    private WebElement btnG;

    private WebDriver driver;

    public GoogleSearchPage(WebDriver driver) {

        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }


    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void searchFor(String searchTerm) {
        q.sendKeys(searchTerm);
        btnG.click();
    }

    public void typeSearchTerm(String searchTerm) {
        q.sendKeys(searchTerm);
    }

    public void clickOnSearch() {
        btnG.click();
    }
}



