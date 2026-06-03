package pages;

import locators.Locators;
import org.openqa.selenium.WebDriver;

public class PaResultsPage extends BasePage {

    public PaResultsPage(WebDriver driver) {
        super(driver);
    }

    public PaResultsPage sortBy(String sortOption) {
        waitForClickable(Locators.PA_SORT_DROPDOWN).click();
        clickByJs(Locators.paSortOption(sortOption));
        return this;
    }

    public String getResultCount() {
        return getTextOf(Locators.PA_RESULT_COUNT);
    }

    public PaDetailPage clickFirstResult() {
        waitForClickable(Locators.PA_FIRST_RESULT_LINK).click();
        return new PaDetailPage(driver);
    }

}
