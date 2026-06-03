package tests;

import base.BaseTest;
import constants.Constants;
import org.junit.jupiter.api.Test;
import pages.PaDetailPage;
import pages.PaHomePage;

public class PaPretragaTest extends BaseTest {

    @Test
    public void pretragaPoKriterijumimaIVerifikacijaOglasa() {
        PaDetailPage detailPage = new PaHomePage(driver)
                .selectBrand(Constants.PA_BRAND)
                .selectYearFrom(Constants.PA_YEAR_FROM)
                .selectChassis(Constants.PA_CHASSIS)
                .selectFuel(Constants.PA_FUEL)
                .clickSearch()
                .sortBy(Constants.PA_SORT_BY_PRICE)
                .clickFirstResult()
                .verifyBrand(Constants.PA_EXPECTED_BRAND)
                .verifyYearFrom(Constants.PA_EXPECTED_YEAR_FROM)
                .verifyChassis(Constants.PA_EXPECTED_CHASSIS)
                .verifyFuel(Constants.PA_EXPECTED_FUEL);
    }

}
