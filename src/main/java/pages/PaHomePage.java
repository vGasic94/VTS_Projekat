package pages;

import constants.Constants;
import locators.Locators;
import org.openqa.selenium.WebDriver;

public class PaHomePage extends BasePage {

    public PaHomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(Constants.PA_URL);
    }

    public PaHomePage selectBrand(String brand) {
        waitForClickable(Locators.PA_BRAND_DROPDOWN).click();
        clickByJs(Locators.paDropdownOption("sumo_brand", brand));
        return this;
    }

    public PaHomePage selectYearFrom(String year) {
        waitForClickable(Locators.PA_YEAR_FROM_DROPDOWN).click();
        clickByJs(Locators.paDropdownOption("sumo_year_from", year));
        return this;
    }

    public PaHomePage selectChassis(String chassis) {
        waitForClickable(Locators.PA_CHASSIS_DROPDOWN).click();
        clickByJs(Locators.paDropdownOption("sumo_chassis", chassis));
        return this;
    }

    public PaHomePage selectFuel(String fuel) {
        waitForClickable(Locators.PA_FUEL_DROPDOWN).click();
        clickByJs(Locators.paDropdownOption("sumo_fuel", fuel));
        return this;
    }

    public PaResultsPage clickSearch() {
        clickByJs(Locators.PA_SEARCH_BUTTON);
        return new PaResultsPage(driver);
    }

}
