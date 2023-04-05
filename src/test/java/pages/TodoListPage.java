package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class TodoListPage {
    public TodoListPage(WebDriver driver) {
        this.driver = driver;
    }

    private final WebDriver driver;
    @FindBy(xpath = "//input[@class='new-todo-list']")
    private WebElement todoListNameField;
    @FindBy(xpath = "//ul[@class='todo-list-list']//div")
    private List<WebElement> todoList;

    public void createTodoList(String name) {
        todoListNameField.click();
        todoListNameField.sendKeys(name);
        todoListNameField.sendKeys(Keys.ENTER);
    }

    public List<TodoList> getAllTodoLists() {
        List<TodoList> todoLists = new ArrayList<>();
        todoList.forEach(l -> todoLists.add(new TodoList(l, driver)));
        return todoLists;
    }
}
