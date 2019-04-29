package domainObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

public class DomainObjects {
    public static WebDriver driver;
    public String browser;
    public static String verizonURL;
    public String verizonUserName;
    public String VerizonPassword;
    public String verizonSQA;
    public String environment;
    public String testDataPath;

    public static Logger log = Logger.getLogger("MyVerizon");

    public void loadPropertiesFile() throws IOException {
        File file = new File("./Config/config.properties");
        FileInputStream fin = new FileInputStream(file);

        Properties prop = new Properties();
        prop.load(fin);
        browser = prop.getProperty("browser");
        environment = prop.getProperty("environment");
        testDataPath = prop.getProperty("testDataPath");
    }

    public void initializelog4j() {
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

}
