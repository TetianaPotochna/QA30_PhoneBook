package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
        //if SignOut present ---> Logout
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finished logout");
        }
    }

    @Test
    public void loginSuccess() {
        logger.info("Start test with name 'loginSuccess'");
        logger.info("Test data ---> email: 'dusm6@gmail.com' & password 'Tt123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dusm6@gmail.com", "Tt123456$");
        app.getHelperUser().submitLogin();

        //Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element 'Sign out' present");
    }

    @Test
    public void loginSuccessUser(){
        logger.info("Test data ---> email: 'dusm6@gmail.com' & password 'Tt123456$'");
        User user = new User().setEmail("dusm6@gmail.com").setPassword("Tt123456$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element 'Sign out' present");
    }

    @Test
    public void loginSuccessModel() {
        logger.info("Test data ---> email: 'dusm6@gmail.com' & password 'Tt123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dusm6@gmail.com", "Tt123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }
    //NEGATIVE TESTS
    @Test
    public void loginWrongEmail(){
        logger.info("Test data ---> email: 'dusm6gmail.com' & password 'Tt123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dusm6gmail.com", "Tt123456$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert present with error text 'Wrong email or password");
    }
    @Test
    public void loginWrongPassword(){
        logger.info("Test data ---> email: 'dusm6@gmail.com' & password 'Tt12$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dusm6@gmail.com", "Tt12$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
    @Test

    public void loginWrongUnregisteredUser(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dusm13@gmail.com", "Tt123456$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
}
