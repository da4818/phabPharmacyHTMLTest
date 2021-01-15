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

    @Test
    public void checkMapHeader() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/map");
        WebElement header = driver.findElement(By.name("mapResponse"));
        Assert.assertEquals(header.getText(),"Cold and Flu");
        driver.quit();
    }

    @Test
    public void checkProductList() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/map");

        Actions a = new Actions(driver);
        WebElement browse = driver.findElement(By.name("Browse"));
        WebElement dropDown = driver.findElement(By.className("dropdown-content"));
        List<WebElement> dropDownElements = dropDown.findElements(By.xpath(".//*"));

        for (int i=0;i<dropDownElements.size();i++){
            a.moveToElement(browse).build().perform();
            a.moveToElement(dropDownElements.get(i)).click().perform();
            System.out.println((i+1)+":"+driver.getCurrentUrl());
        }

    }

/*    @Test
    public void navigatePages(){
        WebDriver driver = new HtmlUnitDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Not needed for this test
        driver.get("https://phabpharmacy.herokuapp.com/login");
        //Back to Homepage
        driver.findElement(By.linkText("Home")).click();
        Assert.assertEquals(driver.getTitle(),"Home");
        //Back to Register page
        driver.findElement(By.linkText("Register")).click();
        Assert.assertEquals(driver.getTitle(),"Register");
        //Back to Map page
        driver.findElement(By.linkText("In-Store")).click();
        Assert.assertEquals(driver.getTitle(),"Find Items In-Store");
        //Back to Browse page - having some issues as Browse isn't a hyperlink like the others but a button that calls a function
        driver.findElement(By.name("Browse")).click();
        //Assert.assertEquals(driver.findElement(By.name("Browse")).getText(),"Browse");
        Assert.assertEquals(driver.getTitle(),"Browse");
        //Back to Basket page
        driver.findElement(By.name("Basket")).click();
        Assert.assertEquals(driver.getTitle(),"Basket");
        // Order, AmendDetails pages aren't accessible through Login - only from Basket
        driver.quit();
    }*/
}
