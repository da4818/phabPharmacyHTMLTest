import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Main {
    public static void main(String[] args){
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/map"); // Navigate to website
        // Submit the query. Webdriver searches for the form using the text input element automatically
        // No need to locate/find the submit button
        // This code will print the page title
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }
}
