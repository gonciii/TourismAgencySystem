package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoginView extends Layout{
    private JPanel container;
    private JPanel w_top;
    private JPanel w_bottom;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_pass;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitiliaze(400, 400);

        // Login Ekranı için renk ayarları !
        lbl_welcome.setForeground(Color.BLACK);
        lbl_username.setForeground(Color.BLACK.darker());
        lbl_username.setBackground(Color.BLACK);
        lbl_pass.setForeground(Color.BLACK.darker());
        btn_login.setBackground(Color.BLACK);
        btn_login.setForeground(Color.BLACK);
        container.setBackground(Color.ORANGE);
        w_bottom.setBackground(Color.ORANGE);
        w_top.setBackground(Color.ORANGE);



        // Değerlendirme 7 - 8 - 9
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_pass};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_pass.getText());
                if (loginUser == null) {
                    Helper.showMsg("notFound");
                } else {

                    if (Objects.equals(loginUser.getRole(), "Admin")) {
                        AdminView adminView = new AdminView(loginUser);
                    } else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                    }
                    dispose();
                }
            }
        });
    }
}
