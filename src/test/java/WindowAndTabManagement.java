import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class WindowAndTabManagement {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");
        System.out.println("Title  " + driver.getTitle());
    }

    @Test
    public void windowAndTabManagement() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("http://www.automationpractice.pl/index.php?id_category=3&controller=category");
        System.out.println("New Window Title " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowsTabs(){
//        Automatically open & switch to the new window or tab
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        System.out.println("Title  " + driver.getTitle());

//        Work in the new window or tab
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("xoxarem788@ronete.com");
        driver.findElement(By.xpath("//span[normalize-space()='Create an account']")).click();

//        get the window id handles
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> iterate = allWindows.iterator();
        String mainFirstWindow =  iterate.next();

//        switch & work in the main window or tab
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.xpath("//input[@id='search_query_top']")).sendKeys("Shirt");
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();
        System.out.println("Title  " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        try {
            // Pause de 3 secondes (3000 millisecondes)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
