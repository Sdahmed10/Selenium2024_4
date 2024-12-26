import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.log.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConsoleLog {
    ChromeDriver driver ;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void viewBrowserConsoleLog(){
        // Get the DevTools & create a session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable the console logs
        devTools.send(Log.enable());

        // Add a listener for the console logs
        devTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("----------");
            System.out.println("Level: " + logEntry.getLevel());
            System.out.println("Text: " + logEntry.getText());
            System.out.println("Broken Url: " + logEntry.getUrl());
        });

        // Load the AUT
        driver.get("https://the-internet.herokuapp.com/broken_images");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
