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

public class MyVerizonHomePage extends TestBase {
    WebDriverWait wait;

    public MyVerizonHomePage() throws IOException {
        PageFactory.initElements(driver, this);
    }

    @FindAll(value= {@FindBy(partialLinkText="Sign Out")})
    List<WebElement> lnkSignOut;

    @FindAll(value= {@FindBy(xpath="//a[@class='gh-nav'][contains(text(),'My Verizon')]")})
    List<WebElement> lnkMyVerizon;

    public List<WebElement> getLnkMyVerizon() {
        return lnkMyVerizon;
    }

    public List<WebElement> getLnkSignOut() {
        return lnkSignOut;
    }

    public boolean isMyVerizonHomePageDisplayed(){
        wait = new WebDriverWait(driver, 90);
        wait.until(ExpectedConditions.elementToBeClickable(getLnkSignOut().get(0)));
        //	wait.until(ExpectedConditions.elementToBeClickable(getLnkMyVerizon().get(0)));
        if(getLnkMyVerizon().size()>0){
            return true;
        }else{
            return false;
        }
    }
}
