import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MapTest {
    @Test
    public void displayTitle() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/map");
        Assert.assertEquals(driver.getTitle(),"Find Items In-Store"); // Each title matches the URl pattern (except the map, see navigatePages function)
        driver.quit();
    }
    @Test // Would simulate testing for other categories
    public void checkMapHeader() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/map");
        Actions a = new Actions(driver);
        WebElement category = driver.findElement(By.name("Category"));
        WebElement dropDown = driver.findElement(By.className("dropdown-content"));
        List<WebElement> dropDownElements = dropDown.findElements(By.xpath("/html/body/section[1]/div/div"));
        a.moveToElement(category).build().perform();
        a.moveToElement(dropDownElements.get(0)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://phabpharmacy.herokuapp.com/map");
        driver.quit();
    }
    @Test
    public void navigatePages(){
        WebDriver driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Not needed for this test
        driver.get("https://phabpharmacy.herokuapp.com/login");
        //Back to Homepage
        driver.findElement(By.linkText("Home")).click();
        Assert.assertEquals(driver.getTitle(),"Home");
        //Back to Browse page
        Actions a = new Actions(driver);
        WebElement browse = driver.findElement(By.name("Browse"));
        a.moveToElement(browse).click().perform();
        Assert.assertEquals(driver.getTitle(),"Browse");
        //Back to Register page
        driver.findElement(By.linkText("Register")).click();
        Assert.assertEquals(driver.getTitle(),"Register");
        //Back to Map page
        driver.findElement(By.linkText("Login")).click();
        Assert.assertEquals(driver.getTitle(),"Login");
        //Back to Basket page
        driver.findElement(By.name("Basket")).click();
        Assert.assertEquals(driver.getTitle(),"Basket");
        // Order, AmendDetails pages aren't accessible through Login - only from Basket
        driver.quit();
    }
    @Test
    public void navigateBrowsePages(){
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/login");
        // Browse subpages - you must hover over the dropdown box before the element become visible to click on it
        WebElement browse = driver.findElement(By.name("Browse"));
        WebElement dropDown = driver.findElement(By.className("dropdown-content"));
        List<WebElement> dropDownElements = dropDown.findElements(By.xpath(".//*"));

        Actions a = new Actions(driver);
        a.moveToElement(browse).build().perform();
        a.moveToElement(dropDownElements.get(0)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://phabpharmacy.herokuapp.com/browse#cold_and_flu");

        a.moveToElement(browse).build().perform();
        a.moveToElement(dropDownElements.get(1)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://phabpharmacy.herokuapp.com/browse#skincare");

        a.moveToElement(browse).build().perform();
        a.moveToElement(dropDownElements.get(2)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://phabpharmacy.herokuapp.com/browse#headaches_and_pain_relief");

        a.moveToElement(browse).build().perform();
        a.moveToElement(dropDownElements.get(3)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://phabpharmacy.herokuapp.com/browse#digestion");

        a.moveToElement(browse).build().perform();
        a.moveToElement(dropDownElements.get(4)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://phabpharmacy.herokuapp.com/browse#allergy");

        a.moveToElement(browse).build().perform();
        a.moveToElement(dropDownElements.get(5)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://phabpharmacy.herokuapp.com/browse#first_aid");
        driver.quit();
    }
    @Test
    public void logOut() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/login");
        //Will need to be logged in prior to this test
        WebElement email = driver.findElement(By.name("email")); // Input field where user enters their email address
        email.sendKeys("js@hotmail.com");
        WebElement password = driver.findElement(By.name("pass"));
        password.sendKeys("qwerty");
        password.submit();
        // Begin testing log out
        driver.get("https://phabpharmacy.herokuapp.com/map");
        List<WebElement> logOutButtons = driver.findElements(By.name("logOut")); // There are 2 buttons with the same name -
        int i = 0; // One with the purpose to log out, and another to prevent a nullPointException in the POST method, in the event the log out button is not pressed
        for (i=0;i<logOutButtons.size();i++){
            if (logOutButtons.get(i).getAttribute("value").equals("Log Out")){
                logOutButtons.get(i).click();
                break;
            }
        }
        WebElement output = driver.findElement(By.className("currentUser"));
        Assert.assertEquals(driver.getTitle(),"Home"); // Should redirect back to the homepage
        Assert.assertEquals(output.getText(),""); // Should output nothing as no one is logged in
        driver.quit();
    }

}
