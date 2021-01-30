package org.kie.lienzo.client.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("NonJREEmulationClassesInClientCode")
public class SomeSeleniumTest {

    private static final String INDEX_HTML = "target/lienzo-webapp-7.48.0-SNAPSHOT/LienzoShowcase.html";
    private static final String INDEX_HTML_PATH = "file:///" + new File(INDEX_HTML).getAbsolutePath();
    private static final boolean HEADLESS = false;

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void openWebapp() {
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(HEADLESS);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        driver.get(INDEX_HTML_PATH);

    }

    @SuppressWarnings("all")
    @Test
    public void test0() throws Exception {
        startTest(0);

        sleep(500);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        long panelLeft = exec("return window.jsLienzo.getPanelOffsetLeft()");
        long panelTop = exec("return window.jsLienzo.getPanelOffsetTop()");
        long x = exec("return window.jsLienzo.getShape('r').x");
        long y = exec("return window.jsLienzo.getShape('r').y");
        assertEquals(100, x);
        assertEquals(100, y);

        log("" + panelLeft);
        log("" + panelTop);

        WebElement panel = getPanel();
        Actions dragRectangle = new Actions(driver);
        dragRectangle
                .moveByOffset((int) panelLeft, (int) panelTop)
                //.moveByOffset((int) x,(int) y)
                .clickAndHold()
                .moveByOffset(100,100)
                .release()
                .perform();

        long tx = exec("return window.jsLienzo.getShape('r').x");
        long ty = exec("return window.jsLienzo.getShape('r').y");
        log("" + tx);
        log("" + ty);
        //assertEquals(200, tx);
        //assertEquals(200, ty);

        // closeWebapp();

    }

    private void closeWebapp() {
        sleep(1500);
        driver.close();
    }

    private WebElement getPanel() {
        //WebElement canvasElement = driver.findElement(By.id("layer_canvas1"));
        //WebElement canvasElement = driver.findElement(By.id("viewPort_div0"));
        WebElement canvasElement = driver.findElement(By.id("layer_canvas3"));
        return canvasElement;
    }

    private void startTest(int index) {
        sleep(1000);
        executeScript("window.jsLienzoExamples.goToExample(" + index + ");");
    }

    private <T> T exec(final String script) {
        try {
            Object result = ((JavascriptExecutor) driver).executeScript(script);
            return (T) result;
        } catch (Exception e) {
            log("Exception during JS execution: " + e.getMessage());
        }
        return null;
    }

    private void executeScript(final String script) {
        try {
            ((JavascriptExecutor) driver).executeScript(script);
        } catch (Exception e) {
            log("Exception during JS execution: " + e.getMessage());
        }
    }

    private WebDriverWait waitOperation() {
        return new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
    }
    private static void log(String s) {
        System.err.println(s);
    }
}
