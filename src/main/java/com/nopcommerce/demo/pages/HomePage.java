package com.nopcommerce.demo.pages;

import com.nopcommerce.demo.utilities.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends Utility {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(linkText = "Log in")
    WebElement loginLink;

    @CacheLookup
    @FindBy(linkText = "Register")
    WebElement registerLink;

    @CacheLookup
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    WebElement clickOnLoginLink;

    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='Log out']")
    WebElement logOutLink;

    @CacheLookup
    @FindBy(xpath = "//div[@class='header-menu']/child::ul[1]/li/a")
    List<WebElement> topMenuList;



    public void clickOnLoginLink() {
        clickOnElement(clickOnLoginLink);
    }

    public void setClickOnLogoutLink() {
        clickOnElement(logOutLink);
    }

    public boolean isLogOutLinkDisplay() {
        return logOutLink.isDisplayed();

    }

    public HomePage clickOnLogOutLink() {
        clickOnElement(logOutLink);
        return new HomePage();
    }

    public boolean isLogInLinkDisplay() {
        return loginLink.isDisplayed();
    }

    public void clickOnRegisterLink() {
        clickOnElement(registerLink);
    }

    public void clickOnMenuTab(String tab) {
        for (WebElement menu : topMenuList) {
            if (menu.getText().contains(tab)) {
                 clickOnElement(menu);
                break;
            }
        }
    }

}


