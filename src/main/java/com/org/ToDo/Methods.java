package com.org.ToDo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class Methods extends Base{

    public Methods(){
        PageFactory.initElements(Base.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//header/input")
    WebElement todoTextField;

    @FindBy(how = How.XPATH, using = "//a[text()='All']")
    WebElement filterAll;

    @FindBy(how = How.XPATH, using = "//a[text()='Active']")
    WebElement filterActive;

    @FindBy(how = How.XPATH, using = "//a[text()='Completed']")
    WebElement filterCompleted;

    public int counter = 0;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void addItem(String item) throws InterruptedException{
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/input")));
            todoTextField.clear();
            todoTextField.sendKeys(item, Keys.ENTER);
            verifyAddition(item);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void verifyAddition(String item) throws  InterruptedException{
        try{
            WebElement addedItem = driver.findElement(By.xpath("//label[text()='"+item+"']"));
            if (addedItem.isDisplayed()) {
                counter++;
                System.out.println("Item added on to-do list is *"+item+"* and total items are "+counter);
            }
            else
                System.out.println("Item not added");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void markComplete(String item) throws InterruptedException{
        try{
            filterAll.click();
            WebElement check = driver.findElement(By.xpath("//label[text()='"+item+"']/parent::div/input"));
            check.click();
            verifyComplete(item);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void verifyComplete(String item) throws InterruptedException{
        try{
            WebElement getStat = driver.findElement(By.xpath("//label[text()='"+item+"']/ancestor::li"));
            boolean stat = getStat.getAttribute("class").equalsIgnoreCase("completed");
            int netCount = Integer.parseInt(driver.findElement(By.xpath("//strong")).getText());
            if(stat && (counter-1 == netCount)) {
                counter--;
                System.out.println("Item *"+item+"* is marked complete and remaining tasks are "+counter);
            }
            else
                System.out.println("Item is not marked complete");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void delItem(String item) throws InterruptedException{
        try{
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            filterAll.click();
            WebElement delButton = driver.findElement(By.xpath("//label[text()='"+item+"']/parent::div/button"));
            jse.executeScript("arguments[0].click()",delButton);
            assertDel(item);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void assertDel(String item) throws InterruptedException{
        try{
            WebElement entry = driver.findElement(By.xpath("//label[text()='"+item+"']"));
            System.out.println("Item *"+item+"* is present in the list");
        }
        catch (Exception e)
        {
            System.out.println("Item *"+item+"* is deleted from the list");
        }
    }

    public boolean checkStat(String filter) throws InterruptedException{
        try{
            ArrayList<WebElement> allElements = new ArrayList<WebElement>(driver.findElements(By.xpath("//app-todo-item/li")));
            switch (filter)
            {
                case "Active": for(WebElement element:allElements) {
                                String clVal = element.getAttribute("class");
                                if (!clVal.equalsIgnoreCase("")) {
                                    return false;
                                    }
                                }
                                return true;
                case "Completed": for(WebElement element:allElements) {
                                    String clVal = element.getAttribute("class");
                                    if (!clVal.equalsIgnoreCase("Completed")) {
                                        return false;
                                        }
                                    }
                                    return true;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void clickActive() throws InterruptedException{
        try{
            filterActive.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-todo-item/li")));
            boolean ch = checkStat("Active");
            if(ch)
                System.out.println("Active Filter: All items are in Active state");
            else
                System.out.println("Active Filter: List includes completed items");
        }
        catch (Exception e)
        {
            System.out.println("Active Filter: No items present in the list");
        }
    }

    public void clickCompleted() throws InterruptedException{
        try{
            filterCompleted.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-todo-item/li")));
            boolean ch = checkStat("Completed");
            if(ch)
                System.out.println("Completed Filter: All items are in Completed state");
            else
                System.out.println("Completed Filter: List includes active items (OR items not found)");
        }
        catch (Exception e)
        {
            System.out.println("Completed Filter: No items present in the list");
        }
    }


}
