package base.utils;

import base.requests.Request;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    protected Request request;

    @BeforeMethod(alwaysRun = true)
    public void enviromentSetUp(){
        request = new Request();
    }
    public Logger log = Logger.getLogger(BaseTest.class);
}
