package task4;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import utils.WaitUtil;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.Wait;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class UnloadEventsHandler {
    /* Case: issue is caused by alert message and might be raised by beforeunload event */
    public static String AcceptAlertAndGetMessage(Duration waitTimeout, Duration pollingInterval) {
        try {
            var alert = Wait()
                    .withTimeout(waitTimeout)
                    .pollingEvery(pollingInterval)
                    .ignoring(TimeoutException.class)
                    .ignoring(NoSuchElementException.class)
                    .until((alertIsPresent()));
            var message = alert.getText();
            alert.accept();

            return message;
        } catch (TimeoutException ignored) {
            return null;
        }
    }

    /* Case 2: issue is caused by the progress spinner// wait till locator for the button we click
    is not present on the page we should get to (Duration timeouts are manageable)
     */
    public static void waitTillElementDisappears(String хPathLocator) {
        WaitUtil.getFluentWait(ofSeconds(10), ofSeconds(5))
                .until(invisibilityOfElementLocated(By.xpath(хPathLocator)));
    }

    /* Case 3: we compare uuid of the button we click with the same button on the next page
        if locators are the same -never used in automation and is not recommended according to Selenide java-docs
        you may find implementation in test method
     */



}
