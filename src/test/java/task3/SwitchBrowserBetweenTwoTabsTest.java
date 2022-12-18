package task3;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import runner.BaseTestRunner;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwitchBrowserBetweenTwoTabsTest extends BaseTestRunner {

    @Test
    public void verifyBrowserSwitching() {
        var initialUrl = "https://jobs.dou.ua/";
        var secondUrl = "https://jobs.dou.ua/companies/kivork/";

        Selenide.open(initialUrl);
        var tabSwitcher = new WindowTabSwitcher();
        tabSwitcher.openInNewTab(secondUrl);
        var newUrl = tabSwitcher.getCurrentUrl();

        assertTrue(secondUrl.equalsIgnoreCase(newUrl),
                format("New url %s should be the same as expected %s", newUrl, secondUrl));

        assertFalse(initialUrl.equalsIgnoreCase(newUrl),
                "The last opened url should not be the same as initial");

        WindowTabSwitcher.switchToFirstOpenedTab();

        assertTrue(tabSwitcher.getCurrentUrl().equalsIgnoreCase(initialUrl),
                "The initial url should be the " + initialUrl);
    }
}
