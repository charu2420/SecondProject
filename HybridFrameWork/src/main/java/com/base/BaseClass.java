package com.base;


	import java.io.File;
	import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
	import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.utility.PropertiesUtils;

	public class BaseClass {
		
	public static WebDriver driver=null;
	public static ExtentReports report=null;
	public static ExtentSparkReporter spark=null;
	public static ExtentTest test=null;
	public static Logger Log = Logger.getLogger("BaseClass");

	public void initalization() {
		System.out.println("browser initialization started");
		Log.info("browser initialization started");
		Log.info("reading property file for the key browser");
		String browser=PropertiesUtils.readproperty("browser");

	if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "chromedriver2.exe");
		driver=new ChromeDriver();
	}
	if(browser.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver=new FirefoxDriver();
	}
		Log.info("maximizing browser window");
		driver.manage().window().maximize();
		Log.info("applying browser level waits");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Log.info("launching JBK offline application");
		driver.get(PropertiesUtils.readproperty("url"));
		/*driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(PropertiesUtils.readproperty("url"));*/
	}
	public void reportInit() {
		Log.info("initalizing extent report");
		report=new ExtentReports();
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/target/ExtentReport.html");
		report.attachReporter(spark);
	}
	public String captureScreenshot(String name) {
		Log.info("capturing screenshot for failed testcases");
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+name+".jpg";

		File dest=new File(path);
		try {
		FileUtils.copyFile(src,dest);
		}
		catch (IOException e) {
		e.printStackTrace();
	}
		return path;
	}
	public String getDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
	String date=sdf.format(new Date());
	return date;
		
	}

}
