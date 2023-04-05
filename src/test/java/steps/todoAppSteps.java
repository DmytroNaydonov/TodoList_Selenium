package steps;

import config.TestConfig;
import factory.ConfigFactory;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.TodoList;
import pages.TodoListPage;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class todoAppSteps {

    private final TestConfig config;
    private final WebDriver driver;
    private final TodoListPage todoListPage;

    public todoAppSteps(DriverFactory driverFactory, ConfigFactory configFactory) {
        this.config = configFactory.create();
        this.driver = driverFactory.create();
        this.todoListPage = PageFactory.initElements(driver, TodoListPage.class);
    }

    @Given("user opens todo lists application")
    public void userOpensTodoListsApplication() {
        driver.get(config.getUrl());
    }

    @And("user creates todo list with the name {string}")
    public void userCreatesTodoList(String name) {
        todoListPage.createTodoList(name);
    }

    @Then("new todo list with name {string} is displayed in the list of todo lists")
    public void newTodoListIsDisplayedInTheListOfTodoLists(String name) {
        List<String> todoListsNames = getTodoListsNames();
        assertThat(todoListsNames, hasItem(name));
    }

    @When("user deletes todo list with name {string}")
    public void userDeletesTodoListWithNameTestList(String name) {
        List<TodoList> todoLists = todoListPage.getAllTodoLists();
        todoLists.stream().filter(l -> l.getName().equals(name)).forEach(TodoList::delete);
    }

    @Then("todo list with name {string} is not displayed in the list of todo lists")
    public void todoListIsNotDisplayedInTheListOfTodoLists(String name) {
        List<String> todoListsNames = getTodoListsNames();
        assertThat(todoListsNames, not(hasItem(name)));
    }

    @After
    public void close(){
        driver.quit();
    }

    private List<String> getTodoListsNames() {
        List<String> todoListsNames = new ArrayList<>();
        todoListPage.getAllTodoLists().forEach(l -> todoListsNames.add(l.getName()));
        return todoListsNames;
    }
}
