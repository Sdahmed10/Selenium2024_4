import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;


public class Geolocation {
    ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void GeoLocation() {
        // Définir les coordonnées avec des types explicites
        Map<String, Object> coordinates = new LinkedHashMap<>();
        coordinates.put("latitude", 48.936050d); // Ajout explicite du "d" pour double
        coordinates.put("longitude", 2.365010d); // Ajout explicite du "d" pour double
        coordinates.put("accuracy", 1); // Pas besoin de changer pour un entier

        // Exécuter la commande CDP
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        System.out.println("Coordinates: " + coordinates);

        // Naviguer vers un site pour vérifier la localisation
        driver.get("https://www.where-am-i.co/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


