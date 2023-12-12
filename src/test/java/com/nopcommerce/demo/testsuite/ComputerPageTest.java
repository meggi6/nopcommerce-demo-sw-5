package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.BuildYourOwnComputerPage;
import com.nopcommerce.demo.pages.ComputerPage;
import com.nopcommerce.demo.pages.DesktopsPage;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

@Listeners(CustomListeners.class)
public class ComputerPageTest extends BaseTest {

    private final String sanity = "sanity";
    HomePage homePage;
    ComputerPage computerPage;
    DesktopsPage desktopsPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopsPage = new DesktopsPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(priority = 1, groups = {sanity})
    public void verifyUserShouldNavigateToComputerPageSuccessfully() {
        //Click on Computer tab
        homePage.clickOnMenuTab("Computers");
        //Verify "Computer" text
        String expectedMessage = "Computers";
        String actualMessage = computerPage.getPageTitleText();
        Assert.assertEquals(expectedMessage, actualMessage, "Computers page not displayed");
    }

    @Test(priority = 2, groups = {"smoke"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //Click on Computer tab
        homePage.clickOnMenuTab("Computers");
        //Click on Desktops link
        computerPage.clickOnSubMenu("Desktops");
        //Verify "Desktops" text
        String expectedMessage = "Desktops";
        String actualMessage = desktopsPage.getPageTitleText();
        Assert.assertEquals(expectedMessage, actualMessage, "Desktops page not displayed");
    }

    @Test(dataProvider = "buildYourComputerData", dataProviderClass = TestData.class, priority = 3, groups = {"regression"})
    public void verifyThatUserShouldBuildYourOwnComputerAndAddToCartSuccessfully(String processor, String ram,
                                                                                 String hdd, String os, String software) throws InterruptedException {
        //Click on Computer tab
        homePage.clickOnMenuTab("Computers");
        //Click on Desktops link
        computerPage.clickOnSubMenu("Desktops");
        //Click on product name "Build your own computer"
        desktopsPage.selectProduct("Build your own computer");
        //Select processor "processor"
        buildYourOwnComputerPage.selectProcessor(processor);
        //Select RAM "ram"
        buildYourOwnComputerPage.selectRam(ram);
        //Select HDD "hdd"
        buildYourOwnComputerPage.selectHDD(hdd);
        //Select OS "os"
        buildYourOwnComputerPage.selectOS(os);
        //Select Software "software"
        buildYourOwnComputerPage.selectSoftware(software);
        //Click on "ADD TO CART" Button
        buildYourOwnComputerPage.clickOnAddToCartButton();
        //Verify message "The product has been added to your shopping cart"
        String expectedMessage = "The product has been added to your shopping cart";
        Thread.sleep(3000);
        String actualMessage = buildYourOwnComputerPage.getProductAddedMessage();
        Assert.assertEquals(expectedMessage, actualMessage, "Product does not added to cart");
    }



}
