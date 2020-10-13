package seleniumtest.main;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class KittenTest {

	public static WebDriver driver;

	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);
		cOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		return cOptions;
	}

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\azwad\\Desktop\\Work\\QA Consulting\\Java\\seleniumtest\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
	}

	@Test
	public void taskOne() {
		driver.get("http://thedemosite.co.uk/");

		WebElement addUserLink = driver.findElement(By.linkText("3. Add a User"));
		addUserLink.click();

		WebElement userInput = driver.findElement(By.name("username"));
		WebElement passInput = driver.findElement(By.name("password"));
		WebElement submitBtn = driver.findElement(By.name("FormsButton2"));
		userInput.sendKeys("guest");
		passInput.sendKeys("guest");
		submitBtn.click();

		WebElement loginLink = driver.findElement(By.linkText("4. Login"));
		loginLink.click();

		WebElement uLoginInput = driver.findElement(By.name("username"));
		WebElement pLoginInput = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("FormsButton2"));
		uLoginInput.sendKeys("guest");
		pLoginInput.sendKeys("guest");
		loginBtn.click();

		WebElement checkSucc = driver
				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		assertEquals("**Successful Login**", checkSucc.getText());

	}

	@Test
	public void taskTwo() throws Exception {

		driver.get("http://automationpractice.com/index.php");

		WebElement searchInput = driver.findElement(By.id("search_query_top"));
		searchInput.sendKeys("Dress");
		Thread.sleep(5000);

		WebElement dropDownMenu = driver.findElement(By.className("ac_results"));
		assertEquals(true, dropDownMenu.isDisplayed());

		WebElement searchBtn = driver.findElement(By.name("submit_search"));
		searchBtn.click();

	}

	@After
	public void cleanUp() {
		driver.quit();
	}

}
