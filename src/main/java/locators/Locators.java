package locators;

import org.openqa.selenium.By;

public class Locators {


    // =====================
    // PA - Home Page
    // =====================
    public static final By PA_BRAND_DROPDOWN = By.xpath("//div[contains(@class,'sumo_brand')]");
    public static final By PA_YEAR_FROM_DROPDOWN = By.xpath("//div[contains(@class,'sumo_year_from')]");
    public static final By PA_CHASSIS_DROPDOWN = By.xpath("//div[contains(@class,'sumo_chassis')]");
    public static final By PA_FUEL_DROPDOWN = By.xpath("//div[contains(@class,'sumo_fuel')]");
    public static final By PA_SEARCH_BUTTON = By.xpath("//button[contains(@class,'js-search-buttons') and not(contains(@class,'advance'))]");

    // Dinamicki lokatori za opcije SumoSelect dropdowna
    public static By paDropdownOption(String dropdownClass, String optionText) {
        return By.xpath(String.format("//div[contains(@class,'%s')]//label[text()='%s']", dropdownClass, optionText));
    }

    // =====================
    // PA - Results Page
    // =====================
    public static final By PA_SORT_DROPDOWN = By.xpath("//div[contains(@class,'sumo_sortOrder')]");
    public static final By PA_FIRST_RESULT_LINK = By.xpath("(//article[contains(@class,'classified')])[1]//a[contains(@class,'ga-title')]");
    public static final By PA_RESULT_COUNT = By.xpath("//p[contains(text(),'Prikazano')]");

    public static By paSortOption(String optionText) {
        return By.xpath(String.format("//div[contains(@class,'sumo_sortOrder')]//label[text()='%s']", optionText));
    }

    // =====================
    // PA - Detail Page
    // =====================
    public static final By PA_DETAIL_BRAND = By.xpath("//div[contains(@class,'uk-width-1-2') and normalize-space(text())='Marka']/following-sibling::div[1]");
    public static final By PA_DETAIL_YEAR = By.xpath("//div[contains(@class,'uk-width-1-2') and normalize-space(text())='Godište']/following-sibling::div[1]");
    public static final By PA_DETAIL_CHASSIS = By.xpath("//div[contains(@class,'uk-width-1-2') and normalize-space(text())='Karoserija']/following-sibling::div[1]");
    public static final By PA_DETAIL_FUEL = By.xpath("//div[contains(@class,'uk-width-1-2') and normalize-space(text())='Gorivo']/following-sibling::div[1]");
    public static final By PA_DETAIL_TITLE = By.xpath("//h1[contains(@class,'classified-title')] | //h1[contains(text(),'godište')]");

}
