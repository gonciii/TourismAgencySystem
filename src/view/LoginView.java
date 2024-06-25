package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout{
    private JPanel container;
    private JLabel lbl_user_name;
    private JTextField txt_fld_user_name;
    private JTextField txt_fld_user_password;
    private JButton loginButton;
    private JLabel lbl_welcome;
    private JLabel lbl_password;
    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400,400,"Login");
        loginButton.addActionListener(e -> {
            JTextField[] chechFieldList = {this.txt_fld_user_name,this.txt_fld_user_password};
            if (Helper.isFieldListEmpty(chechFieldList)) {
                Helper.showMsg("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.txt_fld_user_name.getText(),this.txt_fld_user_password.getText());
                if (loginUser == null) {
                    Helper.showMsg("notFound");
                }else {
                    String role = loginUser.getUserRole();
                    if ("admin".equalsIgnoreCase(role)) {
                        AdminView adminView = new AdminView(loginUser);
                        adminView.setVisible(true);
                    } else if ("employee".equalsIgnoreCase(role)) {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        employeeView.setVisible(true);
                    } else {
                        Helper.showMsg("Invalid role");
                    }
                    // pencere kapatmak için !
                    dispose();
                }
            }
        });
    }


}
