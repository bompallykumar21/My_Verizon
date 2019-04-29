package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class SecretQuestionPage extends TestBase {
    WebDriverWait wait;

    public SecretQuestionPage() throws IOException {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='rememberComputer']")
    WebElement RegisterDeviceOption;

    @FindAll(value= {@FindBy(xpath="//h3[contains(text(),'Secret Question')]")})
    List<WebElement> lblSecretQuestion;

    @FindAll(value= {@FindBy(xpath="//span[contains(text(),'Register this device')]")})
    List<WebElement> chkboxRegisterDevice;

    @FindBy(id = "IDToken1")
    WebElement txtSecretQuestionAnswer;

    @FindBy(xpath="//button[@id = 'otherButton']")
    WebElement btnContinue;


    public WebElement getBtnContinue() {
        return btnContinue;
    }

    public WebElement getTxtSecretQuestionAnswer() {
        return txtSecretQuestionAnswer;
    }

    public List<WebElement> getLblSecretQuestion() {
        return lblSecretQuestion;
    }

    public WebElement getRegisterDeviceOption() {
        return RegisterDeviceOption;
    }

    public List<WebElement> getChkboxRegisterDevice() {
        return chkboxRegisterDevice;
    }

    public boolean isSecretQuestionPageDisplayed() throws InterruptedException{
        wait = new WebDriverWait(driver, 30);
        Thread.sleep(10000);
//		wait.until(ExpectedConditions.visibilityOfAllElements(getLblSecretQuestion()));
        if(getLblSecretQuestion().size()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isRegisterThisDeviceCheckBoxDisplayed() throws InterruptedException{
        if(getChkboxRegisterDevice().size()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isRegisterThisDeviceCheckBoxSelected(){
        if(getRegisterDeviceOption().getAttribute("value").equalsIgnoreCase("y")){
            return true;
        }
        return false;
    }

    public void enterSecretQuestionAnswer(String myVerizon_SQA) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(getTxtSecretQuestionAnswer()));
        getTxtSecretQuestionAnswer().sendKeys(myVerizon_SQA);
    }
}
