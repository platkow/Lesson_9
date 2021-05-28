package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DroppableTest extends TestBase {

    @Test
    public void shouldDragAndDropWithXY() {
        getDriver().get("https://seleniumui.moderntester.pl/droppable.php");

        WebElement drag = getDriver().findElement(By.id("draggable"));
        WebElement drop = getDriver().findElement(By.id("droppable"));

        int xDrag = drag.getLocation().getX();
        int yDrag = drag.getLocation().getY();

        int xDrop = drop.getLocation().getX();
        int yDrop = drop.getLocation().getY();

        int xTarget = xDrop - xDrag;
        int yTarget = yDrop - yDrag;

        Actions actions = new Actions(getDriver());

        actions.dragAndDropBy(drag,xTarget, yTarget)
                .perform();

        assertThat(drop.getText(),
                equalTo("Dropped!"));
    }
}
