package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    //Configurtion
    protected String url;
    protected String userName;
    protected String password;

    public BaseTest() {
        readProperties();
    }

    public void readProperties() {
        try{
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            this.url = (String)properties.get("url");
            this.userName=(String)properties.get("user_name");
            this.password=(String)properties.get("pass");
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail("Config.properties file not found");
        }

    }


}
