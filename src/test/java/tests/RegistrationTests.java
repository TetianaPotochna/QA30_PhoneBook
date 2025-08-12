package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        //if SignOut present ---> Logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
        int r = (int) ((System.currentTimeMillis() / 1000) % 3600);

        User user = new User()
                .setEmail("dusm" + r + "@gmail.com")
                .setPassword("Tt123456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    //NEGATIVE TESTS
@Test
    public void registrationEmptyEmail(){
    User user = new User()
            .setEmail("")
            .setPassword("Tt123456$");
    app.getHelperUser().openLoginRegistrationForm();
    app.getHelperUser().fillLoginRegistrationForm(user);
    app.getHelperUser().submitRegistration();

    Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
}
    @Test
    public void registrationEmptyPassword(){
        User user = new User()
                .setEmail("dusm5@gmail.com")
                .setPassword("");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }

    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .setEmail("dusm5gmail.com")
                .setPassword("Tt123456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertFalse(app.getHelperUser().isLogged());

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }

    @Test
    public void registrationWrongPassword(){
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
        int r = (int) ((System.currentTimeMillis() / 1000) % 3600);

        User user = new User()
                .setEmail("dusm" + r + "@gmail.com")
                .setPassword("Tt1234");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertFalse(app.getHelperUser().isLogged());

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }

    @Test
    public void registrationUserAlreadyExist(){
        User user = new User()
                .setEmail("dusm5@gmail.com")
                .setPassword("Tt123456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }

}
