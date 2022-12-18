package runner;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import utils.LocalRunProvider;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.time.Duration.ofSeconds;

public class BaseTestRunner {
    @BeforeAll
    public static void setUpBrowser() {
        Configuration.timeout = ofSeconds(30).toMillis();
        Configuration.pageLoadTimeout =ofSeconds(90).toMillis();
        Configuration.browser = LocalRunProvider.class.getName();
        open("about:blank");

        getWebDriver()
                .manage()
                .timeouts()
                .scriptTimeout(ofSeconds(60));
    }

    @AfterEach
    public void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
