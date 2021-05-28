package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ConfirmAlertTest extends TestBase {

    @Test
    public void shouldCheckOkAndCancelButtons(){
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        getDriver().findElement(By.id("confirm-alert")).click();
        getDriver().switchTo().alert().accept();

        assertThat(getDriver().findElement(By.id("confirm-label")).getText(), equalTo("You pressed OK!"));

        getDriver().findElement(By.id("confirm-alert")).click();
        getDriver().switchTo().alert().dismiss();

        assertThat(getDriver().findElement(By.id("confirm-label")).getText(), equalTo("You pressed Cancel!"));
    }
}
