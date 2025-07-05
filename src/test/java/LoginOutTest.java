import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginOutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.edgedriver().setup();

        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void Naam(String UserName) {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(UserName);
    }

    private void Pass(String Pass) {
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(Pass);
    }

    private void LoginCk() {
        driver.findElement(By.id("login-button")).getAttribute("value").contains("Login");
        driver.findElement(By.id("login-button")).click();
    }

    private void logout() {
        WebElement burgerMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        burgerMenu.click();

        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));

        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        Naam("standard_user");
        Pass("secret_sauce");
        LoginCk();
        logout();

        Naam("performance_glitch_user");
        Pass("secret_sauce");
        LoginCk();
        logout();

        Naam("locked_out_user");
        Pass("secret_sauce");
        LoginCk();
        Thread.sleep(1000);
        driver.findElement(By.className("error-button")).click();

        Naam("problem_user");
        Pass("secret_sauce");
        LoginCk();
        logout();

        Naam("error_user");
        Pass("secret_sauce");
        LoginCk();
        logout();

        Naam("visual_user");
        Pass("secret_sauce");
        LoginCk();
        logout();

//        Thread.sleep(5000);
//        Naam("Prabal Chowdhury");
//        Pass("Wrong");
//        LoginCk();
//        logout();

    }


    @After
    public void tearDown() {
        //driver.quit();
    }
}

