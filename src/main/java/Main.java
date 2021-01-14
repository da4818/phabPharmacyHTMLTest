import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Main {
    public static void main(String[] args){
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/login"); // Navigate to website
        /*WebElement element = driver.findElement(By.name("email")); // Locating the entry box using its name
        element.sendKeys("Test");
        // Submit the query. Webdriver searches for the form using the text input element automatically
        // No need to locate/find the submit button
        element.submit();*/
        // This code will print the page title
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }
}
