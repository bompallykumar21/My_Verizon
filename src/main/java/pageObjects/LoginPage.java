package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;
import utilities.Utilities;


public class LoginPage extends TestBase {
    WebDriverWait wait;
    Object[][] data;

    public LoginPage() throws IOException {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='IDToken1']")
    WebElement txtUserName;

    @FindBy(xpath = "//input[@id='IDToken2']")
    WebElement txtPassword;

    @FindBy(xpath = "//BUTTON[@id='login-submit']")
    WebElement btnLogin;

    @FindBy(xpath = "//div[@id='dvSSOIFrame']//iframe[@title='myverizon']")
    WebElement frameLogin;

    @FindBy(xpath = "//div[@id='login-content']//iframe[@title='Login Frame']")
    WebElement frameLoginBusiness;

    public WebElement getBtnLogin() {
        return btnLogin;
    }

    public WebElement getTxtUserName() {
        return txtUserName;
    }

    public WebElement getTxtPassword() {
        return txtPassword;
    }

    public WebElement getFrameLogin() {
        return frameLogin;
    }

    public void loginMyVerizon(String userName, String password) throws InterruptedException{

        wait = new WebDriverWait(driver, 30);
        driver.switchTo().frame(getFrameLogin());
        wait.until(ExpectedConditions.elementToBeClickable(getTxtUserName()));
        getTxtUserName().sendKeys(userName);
        getTxtPassword().sendKeys(password);
        getBtnLogin().click();
    }

    public void loginVerizonBussiness(String userName, String password) throws InterruptedException{
        wait = new WebDriverWait(driver, 30);
        driver.switchTo().frame(getFrameLoginBusiness());
        wait.until(ExpectedConditions.elementToBeClickable(getTxtUserName()));
        getTxtUserName().sendKeys(userName);
        getTxtPassword().sendKeys(password);
        getBtnLogin().click();
    }

    public WebElement getFrameLoginBusiness() {
        return frameLoginBusiness;
    }
}
