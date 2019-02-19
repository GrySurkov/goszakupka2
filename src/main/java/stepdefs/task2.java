package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class task2 {

    static WebDriver driver;
    static WebDriverWait wait;
    static FileWriter writer;
    static String window;


    @Given("^i am on \"([^\"]*)\" page$")
    public void i_am_on_page(String url) {
        System.setProperty("webdriver.chrome.driver", "D:\\JavaProject\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 50);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Then("^i see \"([^\"]*)\" and click them$")
    public void i_see_and_click_them(String btn) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.linkText(btn)).click();
        for (String windowHandel : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandel);

        }
        window = driver.getWindowHandle();
    }

    @Then("^i found element by cssSelector \"([^\"]*)\" and click them$")
    public void i_found_element_by_cssSelector_and_click_them(String css) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(css)).click();
    }

    @Then("^i found element by id \"([^\"]*)\" and enter \"([^\"]*)\"$")
    public void i_found_element_by_id_and_entering(String id, String data) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id(id)).sendKeys(data);
    }

    @Then("^i check element by id \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_check_element_by_id_and(String id, String sel) {
        if (driver.findElement(By.id(id)).isSelected()) {
            if (sel.equals("unselect")) {
                driver.findElement(By.id(id)).click();
            }
        } else {
            if (sel.equals("select")) {
                driver.findElement(By.id(id)).click();
            }


        }


    }
    @Then("^i get list of elements by cssSelector \"([^\"]*)\" and choose elements \"([^\"]*)\"$")
    public void i_get_list_of_elements_by_cssSelector_and_choose_elements(String cssSelector, String element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(cssSelector))));
        WebElement ul = driver.findElement(By.cssSelector(cssSelector));
        List<WebElement> li = ul.findElements(By.xpath(".//li"));
        for (int i = 0; i < li.size(); i++) {
            String str = li.get(i).getText();
            if (str.toLowerCase().contains(element.toLowerCase())) {
                if (!(li.get(i).findElement(By.cssSelector("label > input")).isSelected())) {
                    li.get(i).findElement(By.xpath("./label")).click();
                }
            } else {
                if (li.get(i).findElement(By.cssSelector("label > input")).isSelected()) {
                    li.get(i).findElement(By.xpath("./label")).click();
                }
            }
        }
    }

    @Then("^i get result and write there in file$")
    public void i_get_result_and_write_there_in_file() {

        try {
            writer = new FileWriter("result.txt",false);
        } catch (Exception ex){
            System.out.println("Файл не найден");
        }

        String pagesN = driver.findElement(By.cssSelector("body > div.parametrs.margBtm10 > div > div.paginator.greyBox.extendedVariant.margBtm20 > div.paginator.greyBox > p > strong")).getText();
        int iPages = Integer.parseInt(pagesN);
        iPages = iPages/10;
        for(int j = 1; j<iPages; j++) {
            List<WebElement> tables = driver.findElements(By.xpath("//*[@id=\"exceedSphinxPageSizeDiv\"]/div"));

            for (int i = 0; i < tables.size(); i++) {

                tables.get(i).findElement(By.xpath("./div/ul/li[1]")).click();
                for (String windowHandel : driver.getWindowHandles()) {
                    driver.switchTo().window(windowHandel);
                }

                try {
                    writer.append("\n");
                    writer.append(driver.findElement(By.cssSelector("#tab-info > div:nth-child(2) > div > table > tbody > tr:nth-child(4) > td:nth-child(2)")).getText());
                    writer.append("\n");
                    writer.append(driver.findElement(By.cssSelector("#tab-info > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText());
                    writer.append("\n");
                    writer.append(driver.findElement(By.cssSelector("#tab-info > div:nth-child(2) > div > table > tbody > tr:nth-child(10) > td:nth-child(2)")).getText());
                    writer.append("\n");
                } catch (Exception ex) {
                    ex.fillInStackTrace();
                }
                driver.close();
                driver.switchTo().window(window);
            }
            driver.findElement(By.cssSelector("body > div.parametrs.margBtm10 > div > div.paginator.greyBox.extendedVariant.margBtm20 > div.paginator.greyBox > ul > li:nth-child(" + (j+1) + ") > a > span")).click();
        }
    try {
        writer.close();
    }catch (Exception ex){
        ex.fillInStackTrace();
    }


    }
}