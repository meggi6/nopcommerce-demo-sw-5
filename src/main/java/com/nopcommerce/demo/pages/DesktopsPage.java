package com.nopcommerce.demo.pages;

import com.nopcommerce.demo.utilities.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DesktopsPage extends Utility {


    public DesktopsPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(css = "div[class='page-title'] h1")
    WebElement pageTitle;

    @CacheLookup
    @FindBy(id = "products-orderby")
    WebElement sortBy;

    @CacheLookup
    @FindBy(id = "products-pagesize")
    WebElement display;

    @CacheLookup
    @FindBy(xpath = "//div[@class='products-container']//h2/a")
    List<WebElement> productTitleList;


    public String getPageTitleText() {
        String message = getTextFromElement(pageTitle);
        return message;
    }

    public void selectSortBy(String position) {
        selectByVisibleTextFromDropDown(sortBy, position);
    }

    public void selectDisplayPerPage(String number) {
        selectByVisibleTextFromDropDown(display, number);
    }

    public void selectProduct(String productName) {
        for (WebElement product : productTitleList) {
            if (product.getText().equals(productName)) {
                clickOnElement(product);
                break;
            }
        }
    }

}
