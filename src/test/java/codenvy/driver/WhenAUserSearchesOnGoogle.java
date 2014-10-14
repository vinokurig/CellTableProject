package codenvy.driver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class WhenAUserSearchesOnGoogle {

    private GoogleSearchPage page;

    @Before
    public void openTheBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/ChromeDriver");

        page = PageFactory.initElements(new ChromeDriver(), GoogleSearchPage.class);
        page.open("http://google.com.ua/");
    }

    @After
    public void closeTheBrowser() {
        page.close();
    }

    @Test
    public void whenTheUserSearchesForSeleniumTheResultPageTitleShouldContainCats() {
        page.searchFor("selenium");
        assertThat(page.getTitle(), containsString("Google"));
    }
}
