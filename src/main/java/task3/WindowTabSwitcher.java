package task3;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class WindowTabSwitcher {
    private static final WebDriver driver = getWebDriver();


    public static void switchToLastOpenedTab() {
        var windowHandles = new ArrayList<>(driver.getWindowHandles()); /*converts the set of handles
        for all the tabs opened to list considering the order they were opened in */

        Selenide
                .switchTo()                                             // switches the browser focus
                .window(windowHandles.get(windowHandles.size() - 1));   // to the last tab opened in current window
    }

    public static void switchToFirstOpenedTab() {
        var windowHandles = new ArrayList<>(driver.getWindowHandles());
        Selenide
                .switchTo()
                .window(windowHandles.get(0));                         // to the first tab in current window
    }

    public void openInNewTab(String url) {
        executeJavaScript(format("window.open('%s','_blank');", url)); //executes js command to open a blank page in the new tab
        switchToLastOpenedTab();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }   //for assertions purposes only
}
