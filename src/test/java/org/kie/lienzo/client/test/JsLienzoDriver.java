/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.lienzo.client.test;

import java.io.File;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JsLienzoDriver extends JsLienzoExecutor {

    private static final String INDEX_HTML = "target/lienzo-webapp-7.53.0-SNAPSHOT/LienzoShowcase.html";
    private static final String INDEX_HTML_PATH = "file:///" + new File(INDEX_HTML).getAbsolutePath();
    private static final boolean HEADLESS = false;

    private final WebDriver driver;
    private long loadTimeMillis = 1000;

    public static void init() {
        WebDriverManager.firefoxdriver().setup();
    }

    public static JsLienzoDriver devMode() {
        JsLienzoDriver instance = build("http://127.0.0.1:8888/LienzoShowcase.html");
        instance.loadTimeMillis = 3000;
        return instance;
    }

    public static JsLienzoDriver build() {
        return build(INDEX_HTML_PATH);
    }

    public static JsLienzoDriver build(String url) {
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(HEADLESS);
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        driver.get(url);
        return new JsLienzoDriver(driver);
    }

    public JsLienzoDriver(WebDriver driver) {
        super((JavascriptExecutor) driver);
        this.driver = driver;
    }


    public void openTest(int index) {
        sleep(loadTimeMillis);
        executor.executeScript("window.jsLienzoExamples.goToExample(arguments[0])", index);
        sleep(500);
    }

    public void closeTest() {
        sleep(1000);
        driver.close();
    }

    private WebDriverWait waitOperation(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds).getSeconds());
    }

    private WebDriverWait waitAppLoaded() {
        return new WebDriverWait(driver, Duration.ofSeconds(5).getSeconds());
    }

}
