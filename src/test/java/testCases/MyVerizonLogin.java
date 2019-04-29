package testCases;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MyVerizonHomePage;
import pageObjects.SecretQuestionPage;
import testBase.TestBase;

import java.io.IOException;

public class MyVerizonLogin extends TestBase {
    LoginPage loginPage;
    WebDriverWait wait;
    SecretQuestionPage secretQuestionPage;
    MyVerizonHomePage myVerizonHomePage;

    protected MyVerizonLogin() throws IOException, EncryptedDocumentException, InvalidFormatException {
        initialiseApplication("MyVerizon"); // enter url
    }

    @Test
    public void ValidUserValidPassword() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
        log = Logger.getLogger("Valid UserID and Valid Password");
        wait = new WebDriverWait(driver, 30);
        driver.get(verizonURL);
        log.info("My Verizon application launched with URL: "+verizonURL);

        loginPage = new LoginPage();
        loginPage.loginMyVerizon(verizonUserName, VerizonPassword);
        log.info("Entered user credentials and clicked on Submit button: "+verizonUserName);

        secretQuestionPage = new SecretQuestionPage();

        if(secretQuestionPage.isSecretQuestionPageDisplayed()){ //check if SQA page is displayed
            log.info("SecretQuestion Page is displayed");
            secretQuestionPage.enterSecretQuestionAnswer(verizonSQA);
            log.info("SecretQuestion Answer is entered");

            if(secretQuestionPage.isRegisterThisDeviceCheckBoxDisplayed()){  //check if Register this device option is displayed
                log.info("Register this Device checkbox is displayed");

                if(secretQuestionPage.isRegisterThisDeviceCheckBoxSelected()){  //verify if register this device checkbox is checked
                    log.info("Register This Device checkbox is selected");
                    secretQuestionPage.getChkboxRegisterDevice().get(0).click();
                    log.info("Register This Device checkbox is unchecked");
                }
                else{
                    log.info("Register This Device checkbox is not selected");
                }
            }
            else{
                log.info("Register this Device checkbox is not displayed");
            }
            secretQuestionPage.getBtnContinue().click();
            log.info("Continue Button is clicked");
        }
        else {
            log.error("SecretQuestion Page is not displayed");
        }

        myVerizonHomePage=new MyVerizonHomePage();

        if(myVerizonHomePage.isMyVerizonHomePageDisplayed()){  //Check user is signed
            log.info("My Verizon Home Page is displayed");
        }else {
            log.error("My Verizon Home Page is not displayed");
        }
    }
}
