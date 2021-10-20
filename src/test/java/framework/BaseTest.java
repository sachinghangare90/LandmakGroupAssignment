package framework;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static Map<String, String> getConfigMAP() {
        try {
            String path = System.getProperty("user.dir") + "/config.Properties";
            File config = new File(path);
            FileInputStream input;
            input = new FileInputStream(config.getAbsolutePath());
            Properties prop = new Properties();
            prop.load(input);
            Map<String, String> properties = new HashMap<>();
            for (Object key : prop.keySet()) {
                properties.put(key.toString(), prop.getProperty(key.toString()));
            }
            input.close();
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String randomAlphaNumeric()
    {
        return RandomStringUtils.randomAlphanumeric(7);
    }

    public static WebDriver driver;

    public static WebDriver getDriver(){
        String browser = getConfigMAP().get("browser_name");
        switch (browser) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("disable-infobars");
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver();
                break;
            case "IE":
                //System.setProperty("webdriver.ie.driver", Constants.INTERNET_EXPLORER_DRIVER_PATH);
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                //report.info("Internet Explorer browser is launched");
                break;
            case "Firefox":
                //System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                //report.info("Firefox browser is launched");
                break;
            default:
                System.out.println("Path of Driver Executable is not Set for any Browser");
                break;
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().to(getConfigMAP().get("url"));
        waitForMilliSeconds(10000);
        return driver;
    }


    public static void waitForMilliSeconds(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
