package base.utils;

import base.requests.Request;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;


/**
 * The Base test.
 */
public class BaseTest {

    /**
     * an instance of the Request class.
     */
    protected Request request;

    /**
     * Environment set up.
     */
    @BeforeMethod(alwaysRun = true)
    public void environmentSetUp() {
        request = new Request();
    }

    /**
     * The Logger.
     */
    public Logger log = Logger.getLogger(BaseTest.class);
}
