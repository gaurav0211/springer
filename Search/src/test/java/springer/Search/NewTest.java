package springer.Search;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTest {
	static String driverPath = "C:\\Users\\gaurav\\workspace\\springer\\";
	public String appURL="http://link.springer.com";
	public static WebDriver driver;
	String expectedP="7,623 Result(s) for 'Rex Black'";
 	String expectedN="0 Result(s) for '-'";
	
 @BeforeClass
  public void setup() throws Exception {
	 System.setProperty("webdriver.chrome.driver", driverPath
			+ "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
  }
 @Test(priority=0)
 public void positiveTest() throws Exception {
	 	AssertJUnit.assertEquals("Home - Springer", driver.getTitle()); //Verifying correct page is loaded
	 	driver.findElement(By.id("query")).sendKeys("Rex Black");;
	 	driver.findElement(By.id("search")).click();
	 	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 	String actual =driver.findElement(By.className("number-of-search-results-and-search-terms")).getText(); 
	 	System.out.println("Actual result :" +actual);
        AssertJUnit.assertEquals(actual, expectedP);
	 	AssertJUnit.assertFalse(driver.getPageSource().contains("0 Result(s)"));
 }
 
 @Test(priority=1)
 public void negativeTest() throws Exception{
	 	driver.findElement(By.id("query")).clear();
	 	driver.findElement(By.id("query")).sendKeys("-");;
	 	driver.findElement(By.id("search")).click();
	 	driver.getPageSource().contains("0 Result(s) for '-'");
	 	String actual =driver.findElement(By.className("number-of-search-results-and-search-terms")).getText();
        System.out.println("Actual result :" +actual);
        AssertJUnit.assertEquals(actual, expectedN);
}

  @AfterClass
  public void close() throws Exception {
	  driver.quit();
  }

}
