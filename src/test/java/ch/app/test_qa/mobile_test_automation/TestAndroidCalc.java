package ch.app.test_qa.mobile_test_automation;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ch.app.test_qa.core.mobile.AndroidDriverManager;

public class TestAndroidCalc {

	WebDriver driver;

	@Before
	public void beforeEachTest() throws MalformedURLException {
		System.out.println("START TEST ANDROID CALCULATOR");

		// initialize Device Android
		driver = AndroidDriverManager.setUp();

		// and check if something go wrong
		Assert.assertNotEquals(driver, null);
	}

	@After
	public void afterEachTest() {
		System.out.println("END TEST ANDROID CALCULATOR");
		end();
	}

	@Test
	public void testSum() {
		// to clean calculator display
		clearDisplay();

		// Click on number 2 button.
		driver.findElement(By.id("button2")).click();

		// Click on . button.
		driver.findElement(By.id("button10")).click();

		// Click on number 5 button.
		driver.findElement(By.id("button5")).click();

		// Click on + button.
		driver.findElement(By.id("buttonadd")).click();

		// Click on number 5 button.
		driver.findElement(By.id("button5")).click();

		// Click on = button.
		driver.findElement(By.id("buttoneql")).click();

		// Get result from result text box.
		String result = driver.findElement(By.className("android.widget.EditText")).getText();
		System.out.println("Number sum result is : " + result + " expected result: 7.5");
		Assert.assertEquals(Float.parseFloat(result), (float) 2.5 + 5);
	}

	@Test
	public void testMol() {
		// to clean calculator display
		clearDisplay();

		// Click on number 2 button.
		driver.findElement(By.id("button3")).click();

		// Click on + button.
		driver.findElement(By.id("buttonmul")).click();

		// Click on number 5 button.
		driver.findElement(By.id("button0")).click();

		// Click on = button.
		driver.findElement(By.id("buttoneql")).click();

		// Get result from result text box.
		String result = driver.findElement(By.className("android.widget.EditText")).getText();
		System.out.println("Number moltiplicate result is : " + result + " expected result: 0");
		Assert.assertEquals(Float.parseFloat(result), (float) 3 * 0);
	}

	@Test
	public void testSub() {
		// to clean calculator display
		clearDisplay();

		// Click on number 2 button.
		driver.findElement(By.id("button3")).click();

		// Click on + button.
		driver.findElement(By.id("buttonsub")).click();

		// Click on number 5 button.
		driver.findElement(By.id("button9")).click();

		// Click on = button.
		driver.findElement(By.id("buttoneql")).click();

		// Get result from result text box.
		String result = driver.findElement(By.className("android.widget.EditText")).getText();
		System.out.println("Number subtraction result is : " + result + " expected result: -6");
		Assert.assertEquals(Float.parseFloat(result), (float) 3 - 9);
	}

	@Test
	public void testDiv() {
		// to clean calculator display
		clearDisplay();

		// Click on number 2 button.
		driver.findElement(By.id("button3")).click();

		// Click on + button.
		driver.findElement(By.id("buttondiv")).click();

		// Click on number 5 button.
		driver.findElement(By.id("button3")).click();

		// Click on = button.
		driver.findElement(By.id("buttoneql")).click();

		// Get result from result text box.
		String result = driver.findElement(By.className("android.widget.EditText")).getText();
		System.out.println("Number divided result is : " + result + " expected result: 1");
		Assert.assertEquals(Float.parseFloat(result), (float) 3 / 3);
	}

	@Test
	public void testDivByZero() {
		// to clean calculator display
		clearDisplay();

		// Click on number 2 button.
		driver.findElement(By.id("button3")).click();

		// Click on + button.
		driver.findElement(By.id("buttondiv")).click();

		// Click on number 5 button.
		driver.findElement(By.id("button0")).click();

		// Click on = button.
		driver.findElement(By.id("buttoneql")).click();

		// Get result from result text box.
		String result = driver.findElement(By.className("android.widget.EditText")).getText();
		System.out.println(
				"Number divided result is : " + result + " expected result: error divide by zero is not allowed");
		Assert.assertEquals(result, "error divide by zero is not allowed");

	}

	public void end() {
		driver.quit();
	}

	private void clearDisplay() {
		// Click on DELETE/CLR button to clear result text box before running
		// test.
		driver.findElement(By.id("buttonC")).click();
	}
}
