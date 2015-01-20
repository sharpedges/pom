package com.nortal.course.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertTrue;

public class TrivialSearchTest {

    private WebDriver driver;

    private void initWebDriver() {
        driver = new FirefoxDriver();
    }


    @Test
    public void login_onnestus() {
        initWebDriver();
        driver.navigate().to("https://pmp.webmedia.int/epm-portal/"); // ~ driver.get("https://pmp.webmedia.int/epm-portal/");
        // verify something..
        driver.findElement(By.id("viewChangeLink")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("username")).sendKeys("38707170325");
        driver.findElement(By.id("password")).sendKeys("38707170325");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div/p/a/span[1]")).click();

        WebElement dialog = driver.findElement(By.id("msg"));
        String parent = dialog.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/h2[1]")).getText(); //get parent
        assertTrue(parent.contains("Autentimine õnnestus"));
        driver.quit();
    }


    @Test
    public void translate_EN() {
        initWebDriver();

        driver.navigate().to("https://pmp.webmedia.int/epm-portal/"); // ~ driver.get("https://pmp.webmedia.int/epm-portal/");

        // verify something..
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/p/span/a[1]")).click();

        WebElement dialog = driver.findElement(By.id("lang"));
        String parent = dialog.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div[1]/div[2]/div/div/div/p/a/span[1]")).getText(); //get parent
        assertTrue(parent.contains("Continue"));
        driver.quit();
    }

   @Test
    public void shouldLogInMobile() throws InterruptedException {
        initWebDriver();

        driver.navigate().to("https://pmp.webmedia.int/epm-portal/"); // ~ driver.get("https://pmp.webmedia.int/epm-portal/");
        driver.findElement(By.id("phonenumber")).sendKeys("+37200007");
        driver.findElement(By.id("mobileIdButton")).click();
        Thread.sleep(30000);

       String dialog = driver.findElement(By.xpath(".//*[@id='menu']/ul[1]/li[1]/span")).getText();
       assertTrue(dialog.contains("14212128025"));
       driver.quit();
    }



    @Test
    public void quick_Search() {
        initWebDriver();
        driver.navigate().to("https://pmp.webmedia.int/epm-portal/"); // ~ driver.get("https://pmp.webmedia.int/epm-portal/");
        // verify something..
        driver.findElement(By.id("viewChangeLink")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("username")).sendKeys("38707170325");
        driver.findElement(By.id("password")).sendKeys("38707170325");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div/p/a/span[1]")).click();
        driver.findElement(By.xpath(".//*[@id='msg']/p[1]/a")).click();
        driver.findElement(By.xpath(".//*[@id='menu']/ul[1]/li[1]/a[1]")).click();
        driver.findElement(By.xpath(".//*[@id='search']/input")).sendKeys("käideldud");
        driver.findElement(By.xpath(".//*[@id='search']/a")).click();

        String dialog = driver.findElement(By.xpath(".//*[@id='menu']/ul/li")).getText();
        assertTrue(dialog.contains("Käideldud söödakoguste teatis"));
        driver.quit();
    }

    @Test
    public void send_Message_parameters_not_optional() {
        initWebDriver();
        driver.navigate().to("https://pmp.webmedia.int/epm-portal/"); // ~ driver.get("https://pmp.webmedia.int/epm-portal/");
        // verify something..
        driver.findElement(By.id("viewChangeLink")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("username")).sendKeys("38707170325");
        driver.findElement(By.id("password")).sendKeys("38707170325");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div/p/a/span[1]")).click();
        driver.findElement(By.xpath(".//*[@id='msg']/p[1]/a")).click();
        driver.findElement(By.xpath(".//*[@id='menu']/ul[1]/li[1]/a[1]")).click();
        driver.navigate().to("https://pmp.webmedia.int/epm-portal/et/teated/uus");

        driver.findElement(By.xpath(".//*[@id='components---uusTeadeComponent---_components---uusTeadeForm---_elementHolder---teema---_element']")).sendKeys("pealkiri1"); // To select a data from the drop down list.
        driver.findElement(By.id("components---uusTeadeComponent---_saadaTeadeKlient")).click();

        String dialog = driver.findElement(By.xpath(".//*[@id='components---uusTeadeComponent---_components---uusTeadeForm---_elementHolder---asutusKood---_element_error']")).getText();
        assertTrue(dialog.contains("Kohustuslik"));

        String dialog_2 = driver.findElement(By.xpath(".//*[@id='components---uusTeadeComponent---_components---uusTeadeForm---_elementHolder---teadeKommentaar_kommentaar---_element_error']")).getText();
        assertTrue(dialog_2.contains("Kohustuslik"));
        driver.quit();
    }



/*    @Test
    public void send_Message() {
        initWebDriver();
        driver.navigate().to("https://pmp.webmedia.int/epm-portal/"); // ~ driver.get("https://pmp.webmedia.int/epm-portal/");
        // verify something..
        driver.findElement(By.id("viewChangeLink")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("username")).sendKeys("38707170325");
        driver.findElement(By.id("password")).sendKeys("38707170325");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div/p/a/span[1]")).click();
        driver.findElement(By.xpath(".*//*//**//*[@id='msg']/p[1]/a")).click();
        driver.findElement(By.xpath(".*//*//**//*[@id='menu']/ul[1]/li[1]/a[1]")).click();
        driver.navigate().to("https://pmp.webmedia.int/epm-portal/et/teated/uus");

        Select dropDown = new Select(driver.findElement(By.id("components---uusTeadeComponent---_components---uusTeadeForm---_elementHolder---asutusKood---_element")));
        dropDown.selectByValue("PMA");
        driver.findElement(By.xpath("./*//*[@id='components---uusTeadeComponent---_components---uusTeadeForm---_elementHolder---teema---_element']")).sendKeys("pealkiri1"); // To select a data from the drop down list.
        driver.findElement(By.id("components---uusTeadeComponent---_components---uusTeadeForm---_elementHolder---teadeKommentaar_kommentaar---_element")).sendKeys("sisu1"); // To select a data from the drop down list.
        driver.findElement(By.id("components---uusTeadeComponent---_saadaTeadeKlient")).click();
    }*/

}
