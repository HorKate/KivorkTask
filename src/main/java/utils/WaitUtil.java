package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.Wait;

public class WaitUtil {

    public static FluentWait<WebDriver> getFluentWait(Duration waitTimeout, Duration pollingInterval) {
        return Wait()
                .withTimeout(waitTimeout)
                .pollingEvery(pollingInterval)
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class);}
}
