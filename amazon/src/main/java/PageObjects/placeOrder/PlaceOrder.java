package PageObjects.placeOrder;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaceOrder {
	private WebDriver driver;
	
	By searchField=By.xpath("//input[@id='twotabsearchtextbox']");
	By searchButton=By.xpath("//input[@value='Go']");
	By searchResults=By.xpath("//span[contains(text(),'Results')]");
	By productCard=By.xpath("//span[contains(text(),'Mens')]");
	By buynowButton=By.xpath("//input[contains(@id,'buy-now-button')]");
	By loginField=By.xpath("//input[contains(@id,'ap_email')]");
	By passwordField=By.xpath("//input[contains(@id,'ap_password')]");
	By continueButton=By.xpath("//input[contains(@id,'continue')]");
	By SigninButton=By.xpath("//input[contains(@id,'signInSubmit')]");
	By checkoutText=By.xpath("//h1[contains(text(),'Checkout')]");
	By upiRadioButton=By.xpath("//span[contains(text(),'Other UPI Apps')]");
	By upiInput=By.xpath("//input[@placeholder='Ex: MobileNumber@upi']");
	By verifyUpiButton=By.xpath("//input[@name='ppw-widgetEvent:ValidateUpiIdEvent']");
	By verifyUPIText=By.xpath("//span[contains(text(),'Please press continue to complete the purchase.')]");
	By paymentButton=By.xpath("//*[@id=\"orderSummaryPrimaryActionBtn\"]/span/input");
	By placeOrderButton=By.xpath("//*[@id=\"submitOrderButtonId\"]/span/input");
	By checkoutPage=By.xpath("//input[@name='placeYourOrder1']/parent::span[contains(@class,'a-button-inner')]/parent::span[contains(@id,'bottomSubmitOrderButtonId')]");
	
//	By paymentFailedAlert=By.xpath("//h4[contains(text(),'Your last payment attempt failed')]");
	By paymentFailedAlert=By.xpath("div[contains(@class,'a-alert-error')]/div[contains(@class,'a-alert-container')]");
    public PlaceOrder(WebDriver driver) {
    	
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            return driver.findElement(searchField)
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public WebElement toElement(By ele) {
    	return driver.findElement(ele);
    }
    public void sendSearch(String keys) throws InterruptedException {
    	System.out.println("Sending search values to input : Nike Shoes");
    	toElement(searchField).sendKeys(keys);
    	toElement(searchButton).click();
    	new WebDriverWait(driver,90).until(ExpectedConditions.visibilityOfElementLocated(searchResults));
    	
    	
    	
    }
    public void selectProduct() throws InterruptedException {
//    	Actions actions = new Actions(driver);
//    	actions.moveToElement(toElement(productCard)).perform();
    	System.out.println("Selecting the product required");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        //randomly selecting a product
        List<WebElement> products=driver.findElements(productCard);
        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement randomElement = products.get(randomIndex);
        randomElement.click();
    	Set<String> windowHandles = driver.getWindowHandles();
    	String currentWindowHandle = driver.getWindowHandle();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
    	new WebDriverWait(driver,90).until(ExpectedConditions.visibilityOfElementLocated(buynowButton));
    	toElement(buynowButton).click();
    	
    	
    }
    public void loginAction(String id,String password) throws InterruptedException {
    	toElement(loginField).sendKeys(id);
    	toElement(continueButton).click();
    	toElement(passwordField).sendKeys(password);
    	toElement(SigninButton).click();
    	new WebDriverWait(driver,90).until(ExpectedConditions.visibilityOfElementLocated(checkoutText));
    	
    	
    }
    public void paymentSelection(String upiId) throws InterruptedException {
    	toElement(upiRadioButton).click();
    	toElement(upiInput).sendKeys(upiId);
    	toElement(verifyUpiButton).click();
    	new WebDriverWait(driver,90).until(ExpectedConditions.visibilityOfElementLocated(verifyUPIText));
    	toElement(paymentButton).click();
    	new WebDriverWait(driver,90).until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
    	toElement(placeOrderButton).click();
    	Thread.sleep(50000);
    	new WebDriverWait(driver,180).until(ExpectedConditions.visibilityOfElementLocated(checkoutPage));
    	System.out.print("Checkout Page displayed");
    
    	
    }
//    public void selectDelivery() throws InterruptedException {
//    	toElement(deliverButton).click();
//    	new WebDriverWait(driver,90).until(ExpectedConditions.visibilityOfElementLocated(continueButton));
//    	toElement(continueButton).click();
//    	
//    	
//    }
//    
    
}
