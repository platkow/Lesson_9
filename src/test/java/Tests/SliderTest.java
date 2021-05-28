package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SliderTest extends TestBase {

    @Test
    public void shouldMoveSlider() {
        getDriver().get("http://seleniumui.moderntester.pl/slider.php");

        moveTo(50);
        moveTo(30);
        moveTo(30);
        moveTo(80);
    }

    private void moveTo(int expectedSliderValue) {
        WebElement slider = getDriver().findElement(By.id("custom-handle"));

        for (int i = 0; i < expectedSliderValue; i++) {
            slider.sendKeys(checkMoveDirection(expectedSliderValue));
        }
        assertThat(getSliderValue(),equalTo(expectedSliderValue));
    }

    private int getSliderValue() {
        String sliderValue = getDriver().findElement(By.id("custom-handle")).getText();
        return Integer.parseInt(sliderValue);
    }

    private Keys checkMoveDirection(int expectedSliderValue) {
        Keys key = Keys.ENTER;
        int actualSliderValue = getSliderValue();

        if (actualSliderValue < expectedSliderValue) {
            key = Keys.ARROW_RIGHT;
        } else if (actualSliderValue > expectedSliderValue) {
            key = Keys.ARROW_LEFT;
        }
        return key;
    }
}
