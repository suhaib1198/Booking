package Pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BingResults {
	WebDriver driver;
	WebElement searchTxtBox;
	WebElement searchBtn;
	WebElement w;

	
	public BingResults(WebDriver driver) {
		this.driver = driver;
		this.searchBtn = driver.findElement(By.xpath("//*[@id=\"search_icon\"]"));
		this.searchTxtBox = driver.findElement(By.name("q"));
	    w = driver.findElement(By.xpath("//cite[contains(text(),'www.expedia.com')]"));


	}
	public void searchHotle(String txt) {
		this.searchTxtBox.sendKeys(txt);
		this.searchBtn.click();
	}
	
	public List<WebElement> getResults(){
		List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"b_results\"]//*/h2/a"));
		return results;
	}
	
	public List<String> getLinks(){
		List<String> links = new ArrayList<String>();
		List<WebElement> results = this.getResults();
		for(WebElement res: results) {
			links.add(res.getAttribute("href"));
		}
		return links;
	}
	
	public void getL() {
	w.findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*"))
			.findElement(By.cssSelector("div:first-child")).click();
	
}
}