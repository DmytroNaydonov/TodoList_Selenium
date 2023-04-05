Feature: todoApp
  Scenario: User creates and deletes todoList
    Given user opens todo lists application
    And user creates todo list with the name 'TestList'
    Then new todo list with name 'TestList' is displayed in the list of todo lists
    When user deletes todo list with name 'TestList'
    Then todo list with name 'TestList' is not displayed in the list of todo lists