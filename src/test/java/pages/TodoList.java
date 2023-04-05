package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import pages.components.Button;

public class TodoList implements WrapsElement {

    private final WebDriver driver;
    private final WebElement element;
    private final Button deleteButton;
    private static final By DELETE_BUTTON_LOCATOR = By.xpath(".//button");
    private static final By NAME_LOCATOR = By.xpath(".//label");
    @Getter
    private String name;

    public TodoList(WebElement element, WebDriver driver) {
        this.driver = driver;
        this.element = element;
        this.deleteButton = new Button(element.findElement(DELETE_BUTTON_LOCATOR));
        this.name = element.findElement(NAME_LOCATOR).getText();
    }

    public void delete() {
        deleteButton.click();
        driver.switchTo().alert().accept();
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }
}
