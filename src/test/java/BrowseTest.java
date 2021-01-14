import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BrowseTest {
    @Test
    public void displayTitle() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/browse");
        Assert.assertEquals(driver.getTitle(),"Browse"); // Each title matches the URl pattern (except the map, see navigatePages function)
        driver.quit();
    }
    @Test
    public void checkLoggedIn()throws Exception{
        //We want to ensure that no one can add items to their basket if they are not logged in
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/browse");
    }
}
