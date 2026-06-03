package pages;

import locators.Locators;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaDetailPage extends BasePage {

    public PaDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getBrand() {
        return getTextOf(Locators.PA_DETAIL_BRAND);
    }

    public int getYear() {
        String yearText = getTextOf(Locators.PA_DETAIL_YEAR);
        return Integer.parseInt(yearText.replaceAll("[^0-9]", ""));
    }

    public String getChassis() {
        return getTextOf(Locators.PA_DETAIL_CHASSIS);
    }

    public String getFuel() {
        return getTextOf(Locators.PA_DETAIL_FUEL);
    }

    public PaDetailPage verifyBrand(String expectedBrand) {
        assertEquals(expectedBrand, getBrand(), "Marka vozila se ne poklapa");
        return this;
    }

    public PaDetailPage verifyYearFrom(int expectedYearFrom) {
        assertTrue(isYearFromValid(expectedYearFrom), "Godište vozila je pre " + expectedYearFrom);
        return this;
    }

    private boolean isYearFromValid(int expectedYearFrom) {
        return getYear() >= expectedYearFrom;
    }

    public PaDetailPage verifyChassis(String expectedChassis) {
        assertEquals(expectedChassis, getChassis(), "Karoserija se ne poklapa");
        return this;
    }

    public PaDetailPage verifyFuel(String expectedFuel) {
        assertEquals(expectedFuel, getFuel(), "Gorivo se ne poklapa");
        return this;
    }

}
