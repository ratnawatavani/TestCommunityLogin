package testScripts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonSeleniumMethods extends CommunityLogin{

	static WebDriverWait wait = new WebDriverWait(driver, 20);
	
	 public static void clickOnWebElement(WebElement element) throws InterruptedException {
		 Thread.sleep(3000);
		 element.click();
	 }
	 
	 public static void explicitWait(WebElement element) {		 
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
