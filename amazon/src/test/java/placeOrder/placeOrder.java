package placeOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.placeOrder.PlaceOrder;
import io.github.bonigarcia.wdm.WebDriverManager;

public class placeOrder{
	private WebDriver driver;
	private PlaceOrder po;
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        po = new PlaceOrder(driver);
    }

    @Test(priority=1)
    public void launchApp() {
    	System.out.println("Launching amazon application");
        driver.get("https://www.amazon.in/");
    }

    @Test(description="Checking page Load",dependsOnMethods = "launchApp",priority=2)
    public void checkPageLoad() {
        if (po.isSearchInputDisplayed()) {
            System.out.println("Application Loaded successfully");
        } else {
            Assert.fail("Page not loaded");
        }
    }
    
    @Test(description="Performing product search",dependsOnMethods = "checkPageLoad",priority=4)
    public void perform_Search() throws InterruptedException {
    	
        if (po.isSearchInputDisplayed()) {
        	po.sendSearch("Nike Shoes");
        	System.out.println("Search success");
        }
        else {
        	Assert.fail("Search Field not displayed");
        }
    }
    @Test(description="Adding product to cart",dependsOnMethods ="perform_Search",priority=5)
    public void perform_ProductCheckout() throws InterruptedException {
    	try {
        po.selectProduct();
        System.out.println("Product added to cart");
    	}
    	catch(Exception e) {
    		Assert.fail("Product checkout failed");
    		
    	}
        
    }
    @Test(dependsOnMethods = "perform_ProductCheckout",priority=6)
    public void perform_Login() throws InterruptedException {
    	try {
        po.loginAction("id","password");
    	}
    	catch(Exception e) {
    		Assert.fail("Login failed");
    		
    	}
        }
    @Test(dependsOnMethods = "perform_Login",priority=7)
    public void perform_payment() throws InterruptedException {
    	try {
        po.paymentSelection("manviasija96@oksbi");
        System.out.println("Payment selection succesfull: UPI");
    	}catch(Exception e) {
    		Assert.fail("Payment failed");
    		
    	}
    }
    
    
    @AfterClass
    public void AfterClassMethod() {
        driver.quit();
    }
}
	
	
	
	
	

