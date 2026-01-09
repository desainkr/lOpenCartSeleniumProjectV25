package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;
    protected Logger logger;
    protected Properties prop;

    @BeforeClass
    @Parameters({ "browser", "os" })
    public void setup(String browser, String os) throws IOException {
        logger = LogManager.getLogger(this.getClass());

        // Load config
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        prop = new Properties();
        prop.load(file);

        System.out.println("Browser from XML: " + browser + " Operating System is: " + os);
        System.out.println("Execution environment: " + prop.getProperty("execution_env"));

        // Normalize OS string
        os = os.toLowerCase();
        if (!os.equals("windows") && !os.equals("linux") && !os.equals("mac")) {
            throw new RuntimeException("Unsupported OS: " + os);
        }

        String executionEnv = prop.getProperty("execution_env").toLowerCase();

        if (executionEnv.equals("remote")) {
            // -----------------------------
            // REMOTE / Docker Grid
            // -----------------------------
            URL gridUrl = new URL(prop.getProperty("gridURL")); // e.g., http://192.168.1.2:4444

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setPlatformName(os.equals("linux") ? "LINUX" : os.toUpperCase());
                    // Docker/Linux-specific arguments
                    if (os.equals("linux")) {
                        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                    }
                    // Optional flags
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver = new RemoteWebDriver(gridUrl, chromeOptions);
                    break;

                case "firefox":
                    driver = new RemoteWebDriver(gridUrl, new org.openqa.selenium.firefox.FirefoxOptions().setPlatformName(os.equals("linux") ? "LINUX" : os.toUpperCase()));
                    break;

                case "edge":
                    driver = new RemoteWebDriver(gridUrl, new org.openqa.selenium.edge.EdgeOptions().setPlatformName(os.equals("linux") ? "LINUX" : os.toUpperCase()));
                    break;

                default:
                    throw new RuntimeException("Unsupported browser for remote: " + browser);
            }

        } else if (executionEnv.equals("local")) {
            // -----------------------------
            // LOCAL execution
            // -----------------------------
            if (!os.equals("windows")) {
                throw new RuntimeException("Local execution currently only supports Windows");
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--disable-features=PasswordManagerEnabled,PasswordChange");
                    options.addArguments("--disable-notifications", "--disable-infobars");
                    driver = new ChromeDriver(options);
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                default:
                    throw new RuntimeException("Unsupported browser for local execution: " + browser);
            }

        } else {
            throw new RuntimeException("Invalid execution_env: " + executionEnv);
        }

        // -----------------------------
        // COMMON SETTINGS
        // -----------------------------
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("Driver instance: " + driver);

        // Open app URL
        driver.get(prop.getProperty("appURL2"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
