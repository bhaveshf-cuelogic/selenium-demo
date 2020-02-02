package demo.core.datasources;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class OpenEdgePecos {
	WebDriver driver;
	String datasource_url;
	
	public OpenEdgePecos() {
		setDataSourceUrl();
		initializeDriver(true);
	}
    
    public void search_by_npi(String npi) {
    	System.out.println("Searching for provider with NPI : "+npi);
    	try {    		
    		System.out.println("Visiting : "+datasource_url);
    		driver.get(datasource_url);
			
			WebElement npi_input = driver.findElement(By.id("Npi"));
			npi_input.sendKeys(npi);
			npi_input.submit();
			
			System.out.println("Waiting for result to load ...");
			
			Thread.sleep(3000);
			
			WebElement search_result_container = driver.findElement(By.id("search-results"));			
			WebElement ul = search_result_container.findElement(By.tagName("ul"));
			List <WebElement> lis = ul.findElements(By.className("pecos-row"));
			
			WebElement div = lis.get(0).findElement(By.tagName("div"));
			List <WebElement> spans = div.findElements(By.tagName("span"));
			spans.get(2).click();
			
			Thread.sleep(5000);
			
			WebElement cboxLoadedContent = driver.findElement(By.id("cboxLoadedContent"));
			System.out.println("Result ="+cboxLoadedContent.getText());
			
			Actions builder = new Actions(driver);
			
			WebElement e = driver.findElement(By.id("Npi"));
			builder.moveToElement(e).build().perform();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch(InterruptedException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//driver.quit();
		}
    }
    
    private void setDataSourceUrl() {
		datasource_url = "https://opedge.com/Pecos/";
	}
	
	private void initializeDriver(Boolean isHeadless) {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(isHeadless);
    	options.addArguments("--incognito");
    	
		File chrome_driver_file = new File("/home/cuelogic.local/bhavesh.furia/chromedriver79");
    	System.setProperty("webdriver.chrome.driver", chrome_driver_file.getAbsolutePath());
    	driver = new ChromeDriver(options);
    	driver.manage().window().maximize();
	}
}
