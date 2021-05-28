package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class PromptAlertTest extends TestBase {

    @Test
    public void shouldSentTextToAlertTextBox(){
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        getDriver().findElement(By.id("prompt-alert")).click();
        getDriver().switchTo().alert().sendKeys("Lord Vader");
        getDriver().switchTo().alert().accept();

        assertThat(getDriver().findElement(By.id("prompt-label")).getText(), equalTo("Hello Lord Vader! How are you today?"));
    }
}
