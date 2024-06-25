package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class UserView extends Layout{
    private JPanel container;
    private JLabel lbl_user_name;
    private JTextField fld_user_name;
    private JLabel lb_password;
    private JTextField fld_password;
    private JLabel lbl_role;
    private JTextField fld_role;
    private JLabel lbl_full_name;
    private JTextField fld_full_name;
    private JButton btn_user_save;
    private UserManager userManager;
    private User user;

    public UserView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(300,450,"User");
        this.user = user;

        if ( user != null) {
            this.fld_user_name.setText(user.getUserName());
            this.fld_password.setText(user.getUserPassword());
            this.fld_role.setText(user.getUserRole());
            this.fld_full_name.setText(user.getUserFullName());
        }

        // for save button :
        btn_user_save.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_user_name,this.fld_password,this.fld_role,this.fld_full_name};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                boolean result = true;
                if(this.user == null) {
                    User obj = new User(this.fld_user_name.getText(),this.fld_password.getText(),this.fld_role.getText(),this.fld_full_name.getText());
                    result = this.userManager.save(obj);

                } else {
                    this.user.setUserName(this.fld_user_name.getText());
                    this.user.setUserPassword(this.fld_password.getText());
                    this.user.setUserRole(this.fld_role.getText());
                    this.user.setUserFullName(this.fld_full_name.getText());


                    result = this.userManager.update(this.user);
                }

                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
    }


}
