package com.tests;


import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.LoginPage;


public class LoginTest extends BaseClass {
public LoginPage lp=null;
	@BeforeSuite
public void setup() {
		Log.info("inside BeforeSuite");
	initalization();
	reportInit();
	lp=new LoginPage(driver);
}
@AfterSuite
public void tearDown() { // used for saving report
	report.flush();
}
@Test(priority=1)
public void loginTest() {
	Log.info("excuting loginTest ");
	lp.loginToApplication();
	/*driver.findElement(By.id("email")).sendKeys(PropertiesUtils.readproperty("username"));
driver.findElement(By.id("password")).sendKeys(PropertiesUtils.readproperty("password"));
driver.findElement(By.xpath("//button")).click();*/
	Log.info("login test passed");
}
@Test(priority=2)
public void failTest() {
	Log.info("excuting failed test");
	Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log In");
	Log.info("fail test failed");
}
}

/*public class LoginTest extends BaseClass {
@BeforeSuite
public void setup() {
	initalization();
	reportInit();
}
@AfterSuite
public void tearDown() {
	report.flush();
}
@Test
public void loginTest() {
	driver.findElement(By.id("email")).sendKeys(PropertiesUtils.readproperty("username"));
	driver.findElement(By.id("password")).sendKeys(PropertiesUtils.readproperty("password"));
	driver.findElement(By.xpath("//button")).click();
}
@Test
public void failTest() {
	Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log In");
}
}*/
//url=file:///C:/Users/edge/Downloads/javabykiran-Selenium-Softwares/javabykiran-Selenium-Softwares/Offline%20Website/index.html
//url=file:///D:/JBK/javabykiran-Selenium-Softwares/Offline%20Website/index.html


