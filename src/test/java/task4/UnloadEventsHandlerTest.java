package task4;

import org.junit.jupiter.api.Test;
import page_objects.SeleniumPlaygroundPage;
import runner.BaseTestRunner;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class UnloadEventsHandlerTest extends BaseTestRunner {
    private SeleniumPlaygroundPage seleniumPlayGroundPage = new SeleniumPlaygroundPage();
    private UnloadEventsHandler eventsHandler = new UnloadEventsHandler();

    @Test
    public void verifyHandlingUnloadEvents() {
        seleniumPlayGroundPage
                .open()
                .initiateUnloadEvent();

        var elementUidBeforeRedirect = seleniumPlayGroundPage.getTableDataSearchElementUid();
        var tableDataSearchPage = seleniumPlayGroundPage.openTableDataSearch();


        var message = eventsHandler.AcceptAlertAndGetMessage(ofSeconds(2), ofSeconds(1));

        assertNotNull(message, "The alert message should be accepted");

        eventsHandler.waitTillElementDisappears(seleniumPlayGroundPage.mockSpinnerLocator());
        var elementUidAfterRedirect = tableDataSearchPage.getTableDataSearchElementUid();
        assertNotEquals(elementUidBeforeRedirect, elementUidAfterRedirect);
        assertTrue(tableDataSearchPage.isPageLoaded(), "The 'Table data search' page should be displayed");
    }
}
