package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("dusm5@gmail.com").setPassword("Dudu12345@"));
        }
    }

    @Test
    public void addContactTestAllFields() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Tony" + i)
                .lastName("Swuth")
                .phone("5454545" + i)
                .email("toni" + i + "@gmail.com")
                .address("112 Ave")
                .description("friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/ScreenShots/screen-"+i+".png");
//app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addContactTestRecFields() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Tony" + i)
                .lastName("Swuth")
                .phone("5454545" + i)
                .email("toni" + i + "@gmail.com")
                .address("112 Ave Test Rec")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
     //   app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    //NEGATIVE
    @Test
    public void addContactTestEmptyName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("Swuth")
                .phone("545454569845")
                .email("toni@gmail.com")
                .address("112 Ave")
                .description("test Name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
     //   app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addContactTestEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .phone("545454569845")
                .email("toni@gmail.com")
                .address("112 Ave")
                .description("test Last name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
     //   app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addContactTestWrongPhone() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Swut")
                .phone("")
                .email("toni@gmail.com")
                .address("112 Ave")
                .description("test Phone")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
     //   app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
    }

    @Test
    public void addContactTestWrongEmail() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Swut")
                .phone("545454569845")
                .email("tonigmail.com")
                .address("112 Ave")
                .description("test Email")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
//app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid:"));

    }

    @Test
    public void addContactTestWrongAddress() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Swut")
                .phone("545454569845")
                .email("toni@gmail.com")
                .address("")
                .description("test Address")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

}
