package page_objects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class SeleniumPlaygroundPage {
    public  String SELENIUM_PLAYGROUND_URL = "https://www.lambdatest.com/selenium-playground/";
    public static String tableDataSearchLinkXPath = "//a[text()='Table Data Search']";
    private final SelenideElement TABLE_DATA_SEARCH = $x(tableDataSearchLinkXPath);

    public SeleniumPlaygroundPage open() {
        Selenide.open(SELENIUM_PLAYGROUND_URL);

        return this;
    }

    public SeleniumPlayGroundTableSearchFilterPage openTableDataSearch() {
        TABLE_DATA_SEARCH.click();

        return new SeleniumPlayGroundTableSearchFilterPage();
    }

    public SeleniumPlaygroundPage initiateUnloadEvent(){
        executeJavaScript("window.onbeforeunload = function() {return false;};");

        return this;
    }

    public String mockSpinnerLocator(){
        return "//h1[text()='Selenium Playground']";

    }

    public String getTableDataSearchElementUid() {
        return TABLE_DATA_SEARCH.toString();
    }
}
