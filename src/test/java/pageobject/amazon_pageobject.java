package pageobject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class amazon_pageobject {

	// Pageobject webdriver instance
	public static WebDriver driver = null;

	// Locators using on pageobject file
	String SearchDropdown = "searchDropdownBox";
	String SearchBar = "twotabsearchtextbox";
	String SearchResults = "//div/a/h2";
	String ProductpageTitle = "//span[@id='productTitle']";
	String authorslist = "//span[@class='author notFaded']/span/a[contains(@class,'a-link-normal')]";
	String usedPrice = ".//*[@id='usedOfferAccordionRow']/descendant::span[contains(@class,'a-size-medium a-color-secondary')]";
	String newPrice = "//div[@id='newOfferAccordionRow']/descendant::span[contains(@class,'a-size-medium a-color-price header-price')]";
	String bookEdition = "//span[@id='bookEdition']";
	String stock_status = "//div[@id='availability']/span";

	// Product informations text file initialization
	private static final String FILENAME = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator + "product_data" + File.separator
			+ "product_informations.txt";
	BufferedWriter product_informations;

	// Constructor to assign test case webdriver instance to pageobject
	public amazon_pageobject(WebDriver driver) throws IOException {
		this.driver = driver;
		product_informations = new BufferedWriter(new FileWriter(FILENAME));
	}

	public void launchURL(String url) throws IOException {
		driver.get(url);
		// Verify the right url loaded on browser
		Assert.assertEquals(driver.getCurrentUrl(), url);
	}

	public void selectSearchCategory(String category)
			throws InterruptedException {
		// Select dropdown = new Select(driver.findElement(By
		// .id(SearchDropdown)));
		// dropdown.selectByVisibleText(category);
		driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=data+catalog&rh=n%3A283155%2Ck%3Adata+catalog");
		// WebElement we = driver.findElement(By
		// .xpath("//div[@class='nav-search-facade']/span"));
		// Actions act = new Actions(driver);
		// act.moveToElement(we).click().build().perform();
		// Thread.sleep(1000);
		// act.moveToElement(we).sendKeys("bo").build().perform();
		// we.sendKeys("Books");
		// act.sendKeys(Keys.ARROW_DOWN).build().perform();
		// Thread.sleep(1000);
		// act.sendKeys(Keys.ARROW_DOWN).build().perform();
		// Thread.sleep(1000);
		// act.sendKeys(Keys.ARROW_DOWN).build().perform();
		// Thread.sleep(1000);
		// act.sendKeys(Keys.ARROW_DOWN).build().perform();
		// Thread.sleep(1000);

		// List<WebElement> options =
		// driver.findElements(By.xpath("//div/div/select[@aria-describedby='searchDropdownDescription']/option"));
		// act.moveToElement(options.get(10)).build().perform();
		// WebElement we = driver.findElement(By
		// .id(SearchDropdown));
		// we.click();

	}

	public void searchwithKeyword(String Keyword) {
		WebElement searchbar = driver.findElement(By.id(SearchBar));
		searchbar.sendKeys(Keyword);
		searchbar.submit();
	}

	public void ClickonSearchresult(int resultNumber) throws IOException {
		List<WebElement> listofresults = driver.findElements(By
				.xpath(SearchResults));
		String listPage_Title = listofresults.get(resultNumber).getText();
		listofresults.get(resultNumber).click();
		WebElement productpagetitle = driver.findElement(By
				.xpath(ProductpageTitle));
		// validate the product title on list page and product details page
		Assert.assertEquals(productpagetitle.getText(), listPage_Title);
		// Write book title on text file
		product_informations.write("Book Title: " + productpagetitle.getText());
		product_informations.newLine();
	}

	public void Saveauthors_Details() throws IOException {
		List<WebElement> authors = driver.findElements(By.xpath(authorslist));
		StringBuffer authorslist = new StringBuffer();
		for (int i = 0; i < authors.size(); i++) {
			authorslist.append(authors.get(i).getText());
			authorslist.append(", ");
		}
		// Write author details on text file
		product_informations.write("Book Author: " + authorslist);
		product_informations.newLine();
	}

	public void Save_Buyused_price() throws IOException {
		WebElement buyusedprice = driver.findElement(By.xpath(usedPrice));
		// validate the used price on product details page
		Assert.assertNotNull(buyusedprice.getText());
		// Write product used price on text file
		product_informations
				.write("Book used price: " + buyusedprice.getText());
		product_informations.newLine();
	}

	public void Save_Buynew_price() throws IOException {
		WebElement buynewprice = driver.findElement(By.xpath(newPrice));
		// validate the new price on product details page
		Assert.assertNotNull(buynewprice.getText());
		// Write product new price on text file
		product_informations.write("Book new price: " + buynewprice.getText());
		product_informations.newLine();
	}

	public void Save_bookedition() throws IOException {
		WebElement bookedition = driver.findElement(By.xpath(bookEdition));
		// validate book edition on product details page
		Assert.assertNotNull(bookedition.getText());
		// Write product edition on text file
		product_informations.write("Book Edition: " + bookedition.getText());
		product_informations.newLine();
	}

	public void Save_Stock_status() throws IOException {
		WebElement stockstatus = driver.findElement(By.xpath(stock_status));
		// validate book stock status on product details page
		Assert.assertNotNull(stockstatus.getText());
		// Write product stock status on text file
		product_informations.write("Book Stock status: "
				+ stockstatus.getText());
		product_informations.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wd.get("https://www.amazon.com");
		wd.manage().window().maximize();
		Select dropdown = new Select(wd.findElement(By.id("searchDropdownBox")));
		dropdown.selectByVisibleText("Books");
		WebElement searchbar = wd.findElement(By.id("twotabsearchtextbox"));
		searchbar.sendKeys("data catalog");
		searchbar.submit();
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
		System.out.println(imagetitle.getAttribute("src"));
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
