import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class testnglistener implements ITestListener
{
    log4j_file log = new log4j_file();
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext arg)
    {
        test = extent.createTest(""+arg.getName());
    }

    public void onTestSuccess(ITestResult arg)
    {
        test.pass("The test case has passed");
        log.logger.info(arg.getName() + "is successful");
    }

    public void onTestFailure(ITestResult result)
    {

        test.fail("The test is failed ");
        log.logger.info(result.getName() + " has failed");

    }
}
