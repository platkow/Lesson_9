package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DatePickerTest extends TestBase {

    @Test
    public void shouldPickDate() {
        getDriver().get("https://seleniumui.moderntester.pl/datepicker.php");
        displayDatePicker();

        setNewDate(2021, 5, 29);
        compareDates(2021, 5, 29);
        displayDatePicker();
        setNewDate(2021, 2, 5);
        compareDates(2021, 2, 5);
        displayDatePicker();
        setNewDate(2021, 2, 5);
        compareDates(2021, 2, 5);
        displayDatePicker();
        setNewDate(2020, 11, 1);
        compareDates(2020, 11, 1);
        displayDatePicker();
        setNewDate(2020, 12, 1);
        compareDates(2020, 12, 1);
        displayDatePicker();
        setNewDate(2020, 12, 25);
        compareDates(2020, 12, 25);
        displayDatePicker();
        setNewDate(2022, 2, 1);
        compareDates(2022, 2, 1);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        displayDatePicker();
        setNewDate(currentYear, currentMonth, currentDay);
        compareDates(currentYear, currentMonth, currentDay);
    }

    private void setNewDate(int year, int month, int day) {
        while (true) {
            if (getYear() == year) {
                break;
            } else if (getYear() > year) {
                getDriver().findElement(By.cssSelector("a.ui-datepicker-prev.ui-corner-all")).click();
            } else if (getYear() < year) {
                getDriver().findElement(By.cssSelector("a.ui-datepicker-next.ui-corner-all")).click();
            }
        }

        while (true) {
            if (getMonth() == month) {
                break;
            } else if (getMonth() > month) {
                getDriver().findElement(By.cssSelector("a.ui-datepicker-prev.ui-corner-all")).click();
            } else if (getMonth() < month) {
                getDriver().findElement(By.cssSelector("a.ui-datepicker-next.ui-corner-all")).click();
            }
        }

        List<WebElement> daysOfMonth = getDriver().findElements(By.xpath("//td/a[contains(@class,'ui-state-default')and not(contains(@class,'ui-priority-secondary'))]"));
        for (WebElement dayOfMonth : daysOfMonth) {
            if (Integer.parseInt(dayOfMonth.getText()) == day) {
                dayOfMonth.click();
                break;
            }
        }
    }

    private int getYear() {
        int actualYear = 0;
        if (!isDatePickerOpen()) {
            displayDatePicker();
        }
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(".ui-datepicker-year"))));
        actualYear = Integer.parseInt(getDriver().findElement(By.cssSelector(".ui-datepicker-year")).getText());
        return actualYear;
    }

    private int getMonth() {
        String actMonth = getDriver().findElement(By.cssSelector("span.ui-datepicker-month")).getText();
        int actualMonth = 0;

        switch (actMonth) {
            case "January":
                actualMonth = 1;
                break;
            case "February":
                actualMonth = 2;
                break;
            case "March":
                actualMonth = 3;
                break;
            case "April":
                actualMonth = 4;
                break;
            case "May":
                actualMonth = 5;
                break;
            case "June":
                actualMonth = 6;
                break;
            case "July":
                actualMonth = 7;
                break;
            case "August":
                actualMonth = 8;
                break;
            case "September":
                actualMonth = 9;
                break;
            case "October":
                actualMonth = 10;
                break;
            case "November":
                actualMonth = 11;
                break;
            case "December":
                actualMonth = 12;
                break;
            default:
                System.out.println("Month not set.");
                actualMonth = 0;
        }
        return actualMonth;
    }

    public boolean isDatePickerOpen(){
        return getDriver().findElement(By.id("ui-datepicker-div")).isDisplayed();
    }

    private void displayDatePicker() {
        getDriver().findElement(By.id("datepicker")).click();
    }

    private void compareDates(int year, int month, int day) {
        String expectedDate = formatMonth(month) + "/" + formatDay(day) + "/" + year;
        assertThat(getDriver().findElement(By.id("datepicker")).getAttribute("value"), equalTo(expectedDate));
    }

    private String formatMonth(int month) {
        String formattedMonth = "";
        if (month < 10) {
            formattedMonth = String.format("%02d", month);
        } else {
            formattedMonth = String.valueOf(month);
        }
        return formattedMonth;
    }

    private String formatDay(int day) {
        String formattedDay = "";
        if (day < 10) {
            formattedDay = String.format("%02d", day);
        } else {
            formattedDay = String.valueOf(day);
        }
        return formattedDay;
    }
}

