import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

//Déclaration de la classe
public class Screenshots {
    //Déclaration du WebDriver
    WebDriver driver;

    //Configuration initiale
    //La méthode setUp est exécutée avant chaque test pour configurer l’environnement de test.
    @BeforeMethod
    public void setUp() {
        //Configure automatiquement le pilote Firefox.
        WebDriverManager.firefoxdriver().setup();
        //Initialise un nouveau navigateur Firefox.
        driver = new FirefoxDriver();
        //Agrandit la fenêtre du navigateur pour maximiser l'espace visible.
        driver.manage().window().maximize();
        //Supprime toutes les cookies pour éviter des problèmes liés à des sessions précédentes.
        driver.manage().deleteAllCookies();
        //Ouvre l’URL spécifiée dans le navigateur.
        driver.get("https://applitools.com/");
    }

    @Test
    //Capture d'écran d'un élément spécifique
    public void screenshot() throws IOException {
        WebElement maxCoverage = driver.findElement(By.xpath("//h1[@id='h-maximum-coverage-minimum-time']"));
        //Prend une capture d'écran de l'élément et la sauvegarde dans un fichier temporaire.
        File source = maxCoverage.getScreenshotAs(OutputType.FILE);
        //Crée une instance de fichier pour définir l'emplacement de sauvegarde de l'image.
        File destination = new File("Maximum Coverage.png");
        //Copie le fichier source (temporaire) vers le fichier de destination spécifié.
        FileUtils.copyFile (source, destination);
    }

    @Test
    //Capture d'écran d'une section de la page
    public void screenshot2() throws IOException {
        //Localise une section spécifique de la page par son XPath.
        WebElement applitoolspagesection = driver.findElement(By.xpath("//div[@class='wp-block-group pt-default pb-default']"));
        //Prend une capture d'écran de cette section.
        File source = applitoolspagesection.getScreenshotAs(OutputType.FILE);
        //Sauvegarde l'image dans un fichier nommé page section.png.
        FileUtils.copyFile(source, new File("page section.png"));
    }

    @Test
    //Capture d'écran de la page entière
    public void takefullpagescreenshots() throws IOException {
        //Capture une image de toute la page visible dans Firefox et la sauvegarde temporairement.
        File source = ((FirefoxDriver)driver).getFullPageScreenshotAs(OutputType.FILE);
        //Copie le fichier temporaire dans un fichier nommé Applitools.png.
        FileUtils.copyFile(source, new File("Applitools.png"));
    }

    @AfterMethod
    public void tearDown() {
        //quitter
        driver.quit();
    }
}
