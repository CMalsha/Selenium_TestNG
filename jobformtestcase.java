package Jobformportal;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;



public class jobformtestcase {
	 WebDriver driver;

	    @Test(priority=1)
	    public void openapp() {
	    	
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        driver.get("https://demoqa.com/automation-practice-form");
	    }
	    
	    @Test(priority=2)
	    public void testvalidformsubmission() {
	    	driver.findElement(By.id("firstName")).sendKeys("Shane");
	    	driver.findElement(By.id("lastName")).sendKeys("Fernando");
	    	driver.findElement(By.id("userEmail")).sendKeys("shane@123.com");
	    	driver.findElement(By.xpath("//label[text()='Male']")).click();
	    	driver.findElement(By.id("userNumber")).sendKeys("0771234567");
	    	WebElement subjectField = driver.findElement(By.id("subjectsInput"));
	        subjectField.sendKeys("Maths");
	        subjectField.sendKeys(Keys.ENTER);
	        WebElement hobbiesCheckbox = driver.findElement(By.cssSelector("#hobbies-checkbox-1 + label"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbiesCheckbox);
	    	driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\Admin\\Downloads\\stockvault-waterlilies-in-spring113202.jpg"); 
	    	driver.findElement(By.id("currentAddress")).sendKeys("123, Main Street, Colombo");
	    	
	    	WebElement submitButton = driver.findElement(By.id("submit"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
	    	 

	        // Validate success modal
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-title")));
	        Assert.assertEquals(successMessage.getText(), "Thanks for submitting the form", "Success message mismatch!");
	        System.out.println("Test Case 1: Valid Form Submission - Passed");
	    }
	 
	    	
	    
	    
	    @Test(priority = 3)
	    public void testFormSubmissionWithMandatoryFieldsBlank() {
	        driver.navigate().refresh();

	        // Clear all fields (ensuring mandatory fields are left blank)
	        WebElement firstNameField = driver.findElement(By.id("firstName"));
	        WebElement submitButton = driver.findElement(By.id("submit"));

	        // Scroll to the submit button to ensure visibility
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

	        // Click the submit button
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

	        // Verify that mandatory fields are not allowing form submission
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	        // Check that the modal does not appear
	        boolean isModalDisplayed = false;
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-title")));
	            isModalDisplayed = true;
	        } catch (Exception e) {
	            isModalDisplayed = false;
	        }

	        // Assert that form submission was blocked
	        Assert.assertFalse(isModalDisplayed, "Form submission succeeded even though mandatory fields were blank!");

	        System.out.println("Test Case 2: Form Submission with Mandatory Fields Blank - Passed");
	    }

	    
	    
	    @Test(priority = 4)
	    public void testInvalidEmailFormat() {
	        driver.navigate().refresh();

	        // Fill the form with invalid email
	        driver.findElement(By.id("firstName")).sendKeys("Krishan");
	        driver.findElement(By.id("lastName")).sendKeys("Doe");
	        driver.findElement(By.id("userEmail")).sendKeys("doe123");
	        driver.findElement(By.xpath("//label[text()='Male']")).click();
	        driver.findElement(By.id("userNumber")).sendKeys("0778945123");

	        WebElement submitButton = driver.findElement(By.id("submit"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

	        // Validate that the modal dialog does not appear
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        boolean isModalDisplayed = false;

	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-title")));
	            isModalDisplayed = true;
	        } catch (Exception e) {
	            isModalDisplayed = false;
	        }

	        Assert.assertFalse(isModalDisplayed, "Form submission succeeded with an invalid email!");
	        System.out.println("Test Case 3: Invalid Email Format - Passed");
	    }
	    
	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }

	    }
}

	      

	        
	    
	   



