package task1;

import org.junit.jupiter.api.Test;
import page_objects.SeleniumPlaygroundPage;
import runner.BaseTestRunner;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StaleElementReferenceExceptionHandlerTest extends BaseTestRunner {
    private SeleniumPlaygroundPage seleniumPlaygroundPage = new SeleniumPlaygroundPage();

    @Test
    public void verifyStaleElementReferenceException() {
        var seleniumPlayGroundTableSearchFilterPage = seleniumPlaygroundPage
                .open()
                .openTableDataSearch()
                .inputFilterOption("in progress")
                .navigateBack()
                .openTableDataSearch();

        var taskNumberBeforeFilter = seleniumPlayGroundTableSearchFilterPage.getVisibleTasksNumber();
        var taskNumberAfterFilter = seleniumPlayGroundTableSearchFilterPage
                .inputFilterOptionHandlingException("completed")
                .getVisibleTasksNumber();

        assertNotEquals(taskNumberBeforeFilter, taskNumberAfterFilter, "Filter should be applied " +
                "to the task table");
    }
}