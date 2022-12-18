package task1;

import org.openqa.selenium.StaleElementReferenceException;

import static com.codeborne.selenide.Selenide.*;

public class StaleElementReferenceExceptionHandler {
    public static void inputIgnoringStaleElementReferenceException(String xpath, String inputText) {
        // handling exception by reassigning locator when we need to perform any actions with the element usually helps
        try {
            $x(xpath).setValue(inputText);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
            $x(xpath).setValue(inputText);
        }
    }
}
