package page_objects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import task1.StaleElementReferenceExceptionHandler;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SeleniumPlayGroundTableSearchFilterPage extends SeleniumPlaygroundPage{
    public final String filterInputFieldLocator = "//*[@id='task-table-filter']";
    private final SelenideElement filterInputField = $x(filterInputFieldLocator);

    public SeleniumPlaygroundPage navigateBack() {
        Selenide.back();

        return new SeleniumPlaygroundPage();
    }

    public SeleniumPlayGroundTableSearchFilterPage inputFilterOption(String taskStatusOption) {
        filterInputField.setValue(taskStatusOption);

        return this;
    }

    public SeleniumPlayGroundTableSearchFilterPage inputFilterOptionHandlingException(String taskStatusOption) {
        StaleElementReferenceExceptionHandler.inputIgnoringStaleElementReferenceException(filterInputFieldLocator, taskStatusOption);

        return this;
    }

    public int getVisibleTasksNumber() {
         return $$x("//*[@id='task-table']//tbody//tr").filterBy(visible).size();
    }

    public boolean isPageLoaded() {
        return filterInputField.isDisplayed();
    }
}
