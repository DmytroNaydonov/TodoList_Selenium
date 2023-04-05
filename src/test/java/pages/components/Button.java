package pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public class Button implements WrapsElement {

    private WebElement element;

    public Button(WebElement element) {
        this.element = element;
    }

    public void click() {
        element.click();
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }
}
