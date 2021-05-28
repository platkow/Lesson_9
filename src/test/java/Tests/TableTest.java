package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableTest extends TestBase {

    @Test
    public void shouldPrintDataForHigherThan4000InSwitzerland(){
        getDriver().get("https://seleniumui.moderntester.pl/table.php");

        List<WebElement> tableRows = getDriver().findElements(By.cssSelector("tbody tr"));
        System.out.println("Mountain peaks located in Switzerland with height higher than 4000m are: ");

        for (WebElement row : tableRows) {
            List<WebElement> tableColumns = row.findElements(By.cssSelector("td"));

            if(tableColumns.get(2).getText().equals("Switzerland") && (Integer.parseInt(tableColumns.get(3).getText()) > 4000)){
                String positionInRank = row.findElement(By.cssSelector("th")).getText();
                String peak = tableColumns.get(0).getText();
                String mountainRange = tableColumns.get(1).getText();

                System.out.println("- " + peak + " in mountain range " + mountainRange + " with position " + positionInRank + " in comparative data table.");
            }
        }
    }
}
