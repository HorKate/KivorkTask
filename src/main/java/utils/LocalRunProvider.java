package utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nonnull;
import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class LocalRunProvider implements WebDriverProvider {
    public static List<String> CHROME_OPTIONS = List.of(
//            "no-sandbox",
//            "disable-site-isolation-trials",
//            "ignore-certificate-errors",
//            "disable-popup-blocking",
//            "disable-notifications",
//            "disable-browser-side-navigation",
//            "disable-gpu",
//            "dns-prefetch-disable",
//            "disable-impl-side-painting",
//            "disable-infobars",
//            "disable-dev-shm-usage"
    );

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return getChromeDriver(capabilities);
    }

    private WebDriver getChromeDriver(Capabilities capabilities) {
        chromedriver().setup();
        var chromeOptions = new ChromeOptions()
                .addArguments(CHROME_OPTIONS)
                .merge(capabilities);
        return new ChromeDriver(chromeOptions);
    }
}
