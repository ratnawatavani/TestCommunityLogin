package testScripts;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.utilities.CommunityPOJO;
import com.utilities.Constants;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommunityLogin {

	static WebDriver driver;
	String testDataPath = Constants.jsonFilePath;
	String baseURL;

	@BeforeTest
	public void testSetup() throws Exception {
		System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVERPATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "data-provider")
	public void signUp(String url) throws InterruptedException {
		String Url = Constants.urlStart.concat(url);
		driver.get(Url);
		driver.findElement(By.xpath(Constants.letsBegin)).click();
		String signUpMsg = driver.findElement(By.xpath(Constants.panel1Form)).getText();
		assertEquals(signUpMsg, Constants.expectedMsgSginUp);
		WebElement email = driver.findElement(By.name(Constants.emailName));
		email.clear();
		email.sendKeys(Constants.userEmail);

		WebElement password = driver.findElement(By.name(Constants.passwordName));
		password.clear();
		password.sendKeys(Constants.userPassword);

		Thread.sleep(3000);

		WebElement profileCreatorDropdown = driver.findElement(By.xpath(Constants.profileCreatorDropdown));
		profileCreatorDropdown.click();

		List<WebElement> profileCreatorOptList = driver.findElements(By.xpath(Constants.profileCreatorOptList));
		WebElement selectOpt = profileCreatorOptList.get(3);
		CommonSeleniumMethods.clickOnWebElement(selectOpt);

		WebElement submit = driver.findElement(By.xpath(Constants.submit));
		profileCreatorDropdown.submit();
		
		String basicDetailsMsg =  driver.findElement(By.xpath(Constants.panel1Form)).getText();
		assertEquals(basicDetailsMsg, Constants.expectedMsgBasicDetails);
		
		String currentUrl  = driver.getCurrentUrl();
		String motherTongue  = driver.findElement(By.xpath(Constants.motherTongue)).getText();
		if(currentUrl.equalsIgnoreCase(Constants.marathishaadi)) {
			 
			assertEquals(motherTongue, Constants.marathi); 
		}
		
		else if (currentUrl.equalsIgnoreCase(Constants.gujaratishaadi)) {
			assertEquals(motherTongue, Constants.gujarati); 
		}
		 
	
	}

	@AfterClass
	public void closeDriver() throws Exception {
		driver.close();
	}
  

	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() {
		return new Object[][] { { "www.marathishaadi.com" }, { "www.gujaratishaadi.com" } };
	}

}
