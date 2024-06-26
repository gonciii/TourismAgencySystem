import business.UserManager;
import core.Helper;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        // proje başlatma !

        Helper.setTheme();
        //UserManager userManager = new UserManager();
        LoginView loginView = new LoginView();
    }
}