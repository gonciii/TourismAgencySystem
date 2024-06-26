import business.UserManager;
import core.Helper;
import view.LoginView;


public class App {
    public static void main(String[] args) {
        // proje başlatma !
        // Admin : gonci
        // pass : 0000

        // Employee : kenan
        // pass : 0000



        Helper.setTheme();
        UserManager userManager = new UserManager();
        LoginView loginView = new LoginView();


    }
}