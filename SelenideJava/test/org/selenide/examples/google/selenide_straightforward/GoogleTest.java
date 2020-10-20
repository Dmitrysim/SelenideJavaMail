package org.selenide.examples.google.selenide_straightforward;

import com.codeborne.selenide.junit.TextReport;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {
  WebDriver driver;

  private static String mailUsername = System.getProperty("mail.username", "your mail");
  private static String mailPassword = System.getProperty("mail.password", "your password");

  @Rule
  public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

  @Before
  public void setUp() {
    String currentBrowser = System.getProperty("selenide.browser", "chrome");
    if ("chrome".equals(currentBrowser)) {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
    } else if ("firefox".equals(currentBrowser)) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
    } else if ("safari".equals(currentBrowser)) {
      driver = new SafariDriver();
    } else if ("edge".equals(currentBrowser)) {
      WebDriverManager.edgedriver().setup();
      driver = new EdgeDriver();
    } else if ("ie".equals(currentBrowser)) {
      WebDriverManager.iedriver().setup();
      driver = new InternetExplorerDriver();
    }
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void search_selenide_in_google() {
    open("https://mail.ru/?from=logout");
    login();
    waitUntilPagesIsLoaded();
    //$$(By.className("portal-menu-element__text")).equals("Входящие");

  }

  protected static void waitUntilPagesIsLoaded() {
    $(byText("Loading")).waitUntil(disappears, 80000);
  }

  private static void login() {
    $(By.name("login")).val(mailUsername).pressEnter();
    $(By.name("password")).val(mailPassword).pressEnter();
  }

}
