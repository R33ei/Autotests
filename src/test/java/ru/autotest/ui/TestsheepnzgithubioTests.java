package ru.autotest.ui;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class TestsheepnzgithubioTests {

    private final static String URL = "https://testsheepnz.github.io/";
    private final static WebDriver webDriver = new FirefoxDriver();
    private final JavascriptExecutor js = (JavascriptExecutor) webDriver;

    @Test
    public void test1() {
        webDriver.get(URL + "BasicCalculator.html");

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        // Выбрать сборку (Build) «Prototype»
        new Select(webDriver.findElement(By.id("selectBuild"))).selectByVisibleText("Prototype");
        // Ввести в поле First number значение «2»
        webDriver.findElement(By.id("number1Field")).sendKeys("2");
        // Ввести в поле Second number значение «3»
        webDriver.findElement(By.id("number2Field")).sendKeys("3");
        // Выбрать операцию (Operation) «Subtract»
        new Select(webDriver.findElement(By.id("selectOperationDropdown"))).selectByVisibleText("Subtract");
        // Нажать на кнопку «Calculate»
        webDriver.findElement(By.id("calculateButton")).click();
        WebElement answerFieldElement = webDriver.findElement(By.id("numberAnswerField"));

        assertEquals(answerFieldElement.getAttribute("value"), "-1");
    }

    @Test
    public void test2() {
        webDriver.get(URL + "BasicCalculator.html");
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        new Select(webDriver.findElement(By.id("selectBuild"))).selectByVisibleText("Prototype");
        webDriver.findElement(By.id("number1Field")).sendKeys("gs");
        webDriver.findElement(By.id("number2Field")).sendKeys("bu");
        new Select(webDriver.findElement(By.id("selectOperationDropdown"))).selectByVisibleText("Concatenate");
        webDriver.findElement(By.id("calculateButton")).click();
        WebElement answerFieldElement = webDriver.findElement(By.id("numberAnswerField"));

        assertEquals(answerFieldElement.getAttribute("value"), "gsbu");
    }

    @Test
    public void test3() {
        webDriver.get(URL + "random-number.html");
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        // Выбрать сборку (Select Build) «Demo»
        new Select(webDriver.findElement(By.id("buildNumber"))).selectByVisibleText("Demo");
        // Нажать на кнопку «Roll the dice»
        webDriver.findElement(By.id("rollDiceButton")).click();
        // После нажатия кнопки «Roll the dice» происходит анимация
        // и только после неё появляется текстовое поле для ввода значений.
        // Ждём появления поля для ввода в течении 10 сек, либо пока оно не станет кликабельным
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("numberGuess")));
        // Ввести в поле значение «string».
        webDriver.findElement(By.id("numberGuess")).sendKeys("string");
        // Нажать на кнопку «Submit»
        webDriver.findElement(By.id("submitButton")).click();
        // Получаем html элемент, который отвечает за вывод результата
//        WebElement answerFieldElement = new WebDriverWait(webDriver, Duration.ofSeconds(1))
//                .until(driver -> driver.findElement(By.id("feedbackLabel")));
        WebElement answerFieldElement = new WebDriverWait(webDriver, Duration.ofSeconds(1))
                .until(driver -> driver.findElement(By.id("feedbackLabel")));

        assertEquals(answerFieldElement.getText(), "string: Not a number!");
    }

    @AfterClass
    public static void end() {
        webDriver.close();
    }

}
