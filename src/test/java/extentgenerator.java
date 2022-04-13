import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//class to generate extent report
public class extentgenerator
{
    public static ExtentReports extentReporter()
    {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter("extent.html");
        extentReports.attachReporter(reporter);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("GoRest Assignment Report");
        return extentReports;
    }
}
