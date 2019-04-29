package testBase;

import domainObjects.DomainObjects;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import utilities.Utilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase extends DomainObjects {
    Object[][] data;

    protected TestBase() throws IOException {
        loadPropertiesFile();
        initializelog4j();
    }

    @BeforeClass
    public void launchBrowser() {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-default-apps");
            options.addArguments("--ignore-certificate-errors");
            //			options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        }else
        if(browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }

        //		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    //@AfterClass
	/*public void closeBrowser() throws InterruptedException{
		Thread.sleep(10000);
		driver.quit();
	}*/

    public void initialiseApplication(String SignInFlow) throws EncryptedDocumentException, InvalidFormatException, IOException {
        if (environment.equalsIgnoreCase("sit")) {
            data = Utilities.getDatafromExcel(testDataPath, environment);
            for (int i = 0; i < data.length; i++) {
                if(data[i][0].toString().trim().equalsIgnoreCase(SignInFlow)){
                    verizonURL= data[i][1].toString().trim();
                    verizonUserName= data[i][2].toString().trim();
                    VerizonPassword= data[i][3].toString().trim();
                    verizonSQA= data[i][4].toString().trim();
                    break;
                }
            }
        }
        else if(environment.equalsIgnoreCase("PROD")){
            data = Utilities.getDatafromExcel(testDataPath, environment);
            for (int i = 0; i < data.length; i++) {
                if(data[i][0].toString().trim().equalsIgnoreCase(SignInFlow)){
                    verizonURL= data[i][1].toString().trim();
                    verizonUserName= data[i][2].toString().trim();
                    VerizonPassword= data[i][3].toString().trim();
                    verizonSQA= data[i][4].toString().trim();
                    break;
                }
            }
        }
    }
}
