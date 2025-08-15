package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
//        WebElement loginTab = wd.findElement(By.cssSelector("a[href='/login']"));
//        //xPath -->//a/
//        loginTab.click();
        click(By.cssSelector("a[href='/login']"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
//        WebElement emailInput = wd.findElement(By.name("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);
        type(By.name("email"), email);

//        WebElement passwordInput = wd.findElement(By.xpath("//input[@name='password']"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
        type(By.xpath("//input[@name='password']"), password);
    }
    public void fillLoginRegistrationForm(User user){
        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[@name='password']"), user.getPassword());
    }


    public void submitLogin() {
        click(By.xpath("//button[text()='Login']"));
    }
    public void submitRegistration(){
        click(By.xpath("//button[text()='Registration']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public boolean isNoContactsHere() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
       boolean res = wait.until(ExpectedConditions.textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")), "No Contacts here!"));
       return res;
    }

    public void login(User user){
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
