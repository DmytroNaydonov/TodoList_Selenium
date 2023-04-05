package factory;

import config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
    private TestConfig config;

    public DriverFactory(ConfigFactory configFactory) {
        this.config = configFactory.create();
    }

    public WebDriver create() {
        switch (config.getBrowser().getName()) {
            case "Chrome" -> {
                WebDriverManager.chromedriver().setup();
                val options = new ChromeOptions();
                if (config.getBrowser().getHeadless().equals("true")) {
                    options.addArguments("--headless");
                }
                return new ChromeDriver(options);
            }
            case "Edge" -> {
                WebDriverManager.edgedriver().setup();
                val options = new EdgeOptions();
                if (config.getBrowser().getHeadless().equals("true")) {
                    options.addArguments("--headless");
                }
                return new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Supported browsers are Chrome or Edge");
        }
    }
}
