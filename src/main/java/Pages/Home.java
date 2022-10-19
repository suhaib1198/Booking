package Pages;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home {
	WebDriver driver;
	WebElement chin;
	WebElement chout;
	WebElement search;
	JavascriptExecutor js;


	public Home(WebDriver driver) {

		this.driver =driver;
		js = (JavascriptExecutor) driver;
		chout =driver.findElement((By.xpath("//button[contains(@data-day,'27')]")));
		chin=driver.findElement(By.xpath("//button[contains(@data-day,'25')]"));
		search=driver.findElement(By.xpath("//button[contains(@data-stid,'apply-date-picker')]"));
	}

	public void Reserve() throws IOException, InterruptedException {
		js.executeScript("document.getElementById(\\\"hotels-check-in-btn\\\").click()");
		js.executeScript("document.getElementById(\"hotels-check-in-btn\").click()");
		chin.click();;
		js.executeScript("document.getElementById(\"hotels-check-out-btn\").textContent=\"Oct 27\"");
		chout.click();;
		search.click();;
		
	}


}