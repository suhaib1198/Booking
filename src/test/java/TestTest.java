import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.BingResults;
import Pages.Home;

public class TestTest {
	WebDriver driver;
	ArrayList<String> outputHeaders = new ArrayList<String>();
	ArrayList<ArrayList<String>> outputData = new ArrayList<ArrayList<String>>();
	Home home;
	BingResults br;
	String currentPage;
	String rate;
	List<String[]> data;
	String[] line = new String[3];

	@BeforeSuite
	public void beforeSuite() throws InterruptedException {
		//driver = OpenBrowsers.openchromeWithOptions();
		driver = OpenBrowsers.openBrowser("firefox");
		home=new Home(driver);
		br =new BingResults(driver);
		outputHeaders.add("hotel_id");
		outputHeaders.add("name");
		outputHeaders.add("city");
		outputHeaders.add("address");
		Thread.sleep(10000);
		driver.manage().window().maximize();
	}
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.bing.com/");
	}
	@DataProvider(name = "Data")
	public static Object[][] getData() throws Exception {
		List<String[]> lines = ReadCsvFile.readAllLines("input.csv");
		lines.remove(0);
		Object[][] data = new Object[lines.size()][lines.get(0).length];
		int index = 0;
		for (String[] line : lines) {
			data[index] = line;
			index++;
		}
		return data;
	}

	@Test(dataProvider = "Data")
	public void test(String id, String name, String city, String address) throws IOException, InterruptedException {
		br.searchHotle(name + " " + city + " expedia");
		br.getL();
		currentPage = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentPage);
		line[0] = id;
		line[1] = currentPage;
		home.Reserve();
		data.add(line);
	}

	@AfterSuite
	public void afterSuite() {
		//driver.quit();
		List<String[]> data = new ArrayList<String[]>();
		for(ArrayList<String> row: outputData) {
			String[] row_data = new String[row.size()];
			for(int i= 0;i<row.size();i++) {
				row_data[i] = row.get(i);
			}
			data.add(row_data);
		}
		String[] headers = new String[outputHeaders.size()];
		for(int i= 0;i<outputHeaders.size();i++) {
			headers[i] = outputHeaders.get(i);
		}
		WriteCsvFile.writeDataLineByLine("outputest.csv", data, headers);
		
	}

}