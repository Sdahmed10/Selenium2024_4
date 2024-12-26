import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class RelativeLocators {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testRelativeLocators() throws InterruptedException {
        Thread.sleep(3000);
        WebElement loginPanel = driver.findElement(By.cssSelector(".oxd-sheet.oxd-sheet--rounded.oxd-sheet--gutters.oxd-sheet--gray-lighten-2.orangehrm-demo-credentials"));
        WebElement credentials = driver.findElement(with(
                        By.tagName("div"))
                .above(loginPanel));
        System.out.println(credentials.getText());
    }

    @Test
    public void testRelativeLocators2() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> allSocialMedia = driver.findElements(with(
        By.tagName("svg"))
                .near(By.xpath("//a[@href='https://www.linkedin.com/company/orangehrm/mycompany/']//*[name()='svg']")));
        for (WebElement element : allSocialMedia) {
            System.out.println(element.getText());
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
