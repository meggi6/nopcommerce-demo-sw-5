package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.RegisterPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(CustomListeners.class)
public class RegisterPageTest extends BaseTest {

    HomePage homePage;
    RegisterPage registerPage;
    public static String email = getRandomString(5) + "@gmail.com";

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
    }

    @Test(priority = 1, groups = {"sanity"})
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //Click on Register Link
        homePage.clickOnRegisterLink();
        //Verify "Register" text
        Assert.assertEquals("Register", registerPage.getRegisterText(), "Register page not displayed");

    }

    @Test(priority = 2, groups = {"smoke"})
    public void verifyThatFirstNameLastNameEmailPasswordAndConfirmPasswordFieldsAreMandatory() {
        SoftAssert softAssert = new SoftAssert();
        homePage.clickOnRegisterLink();
        //Click on Register Link
        registerPage.clickOnRegisterButton();
        //Click on "REGISTER" button
        //Verify the error message "First name is required."
        softAssert.assertEquals("First name is required.", registerPage.getValidationErrorMessageForField("FirstName"),
                "FirstName field error message not displayed");
        //Verify the error message "Last name is required."
        softAssert.assertEquals("Last name is required.", registerPage.getValidationErrorMessageForField("LastName"),
                "LastName field error message not displayed");
        //Verify the error message "Email is required."
        softAssert.assertEquals("Email is required.", registerPage.getValidationErrorMessageForField("Email"),
                "Email field error message not displayed");
        //Verify the error message "Password is required."
        softAssert.assertEquals("Password is required.", registerPage.getValidationErrorMessageForField("Password"),
                "Password field error message not displayed");
        //Verify the error message "Password is required."
        softAssert.assertEquals("Password is required.", registerPage.getValidationErrorMessageForField("ConfirmPassword"),
                "ConfirmPassword field error message not displayed");
        softAssert.assertAll();

    }

    @Test(priority = 3, groups = {"regression"})
    public void verifyThatUserShouldCreateAccountSuccessfully() throws InterruptedException {
        //Click on Register Link
        homePage.clickOnRegisterLink();
        //Select gender "Female"
        registerPage.selectGender("male");
        //Enter firstname
        registerPage.enterFirstName("ajay");
        //Enter lastname
        registerPage.enterLastName("kumar");
        Thread.sleep(3000);
        //Select day, month and year
        registerPage.selectDateOfBirth("10", "February", "1956");
        //enter email
        registerPage.enterEmail(email);
        Thread.sleep(3000);
        // enter password
        registerPage.enterPassword("ajay@123");
        Thread.sleep(3000);
        // enter confirm password
        registerPage.enterConfirmPassword("ajay@123");
        // click on  REGISTER BUTTON
        registerPage.clickOnRegisterButton();
        Thread.sleep(3000);
        //Verify message "Your registration completed"
        Assert.assertEquals("Your registration completed", registerPage.getYourRegCompletedText(), "Registration not successful");
        registerPage.clickOnContinueButton();
    }
}
