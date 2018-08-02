package testcase;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + File.separator + "src"
						+ File.separator + "test" + File.separator
						+ "resources" + File.separator + "browsers"
						+ File.separator + "geckodriver.exe");
		WebDriver wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wd.get("https://www.amazon.com");
		wd.manage().window().maximize();
		wd.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=data+catalog&rh=n%3A283155%2Ck%3Adata+catalog");
		List<WebElement> listofresults = wd
				.findElements(By.xpath("//div/a/h2"));
		System.out.println(listofresults.size());
		listofresults.get(0).click();
		WebElement productpagetitle = wd.findElement(By
				.xpath("//span[@id='productTitle']"));
		System.out.println(productpagetitle.getText());
		// Assert.assertEquals(listofresults.get(0).getText(),
		// productpagetitle.getText());
		WebElement imagetitle = wd.findElement(By
				.xpath("//img[@id='imgBlkFront']"));
		System.out.println(imagetitle.getAttribute("alt src"));
		List<WebElement> authors = wd
				.findElements(By
						.xpath("//span[@class='author notFaded']/span/a[contains(@class,'a-link-normal')]"));
		System.out.println(authors.get(0).getText());
		WebElement buyusedprice = wd
				.findElement(By
						.xpath(".//*[@id='usedOfferAccordionRow']/descendant::span[contains(@class,'a-size-medium a-color-secondary')]"));
		System.out.println(buyusedprice.getText());
		WebElement buynewprice = wd
				.findElement(By
						.xpath("//div[@id='newOfferAccordionRow']/descendant::span[contains(@class,'a-size-medium a-color-price header-price')]"));
		System.out.println(buynewprice.getText());
		WebElement bookedition = wd.findElement(By
				.xpath("//span[@id='bookEdition']"));
		System.out.println(bookedition.getText());
		WebElement stockstatus = wd.findElement(By
				.xpath("//div[@id='availability']/span"));
		System.out.println(stockstatus.getText());

	}

	}

