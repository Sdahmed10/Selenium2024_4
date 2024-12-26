import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PositionWithgetRect {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.mytek.tn/");
        System.out.println("Title  " + driver.getTitle());
    }

    @Test
    public void getrect() {
        WebElement logoMyTek = driver.findElement(By.xpath("//img[@title='Logo Mytek']"));
        Rectangle rectlogoMyTek = logoMyTek.getRect();
        System.out.println("x: " + rectlogoMyTek.getX());
        System.out.println("y: " + rectlogoMyTek.getY());
        System.out.println("Width: " + rectlogoMyTek.getWidth());
        System.out.println("Height: " + rectlogoMyTek.getHeight());
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        try {
            Thread.sleep(3000);
        } catch
            (InterruptedException e){
                e.printStackTrace();
            }
            driver.quit();
        }
    }


