package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;

public class AdminView extends Layout {
    private JPanel contanier;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_user;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JComboBox cmb_user_role;
    private JButton btn_user_save;
    private JLabel lbl_pass;
    private JScrollPane scl_left;
    private JComboBox cmb_filter_role;
    private JButton btn_ara;
    private JButton btn_reset;
    private JButton araButton;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu user_menu;
    private Object[] col_user;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.add(contanier);
        this.guiInitiliaze(800, 500);
        this.user = user;

        if (this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText(" Hoşgeldiniz : " + this.user.getUsername().toLowerCase());

        // kullanıcı
        loadUserTable(null);
        loadUserComponent();

        contanier.setBackground(Color.ORANGE);



        this.btn_user_save.addActionListener(e -> {

            boolean result = true;

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_username, this.fld_pass})) {
                Helper.showMsg("fill");
            } else {

                this.user.setUsername(fld_username.getText());
                this.user.setPassword(fld_pass.getText());
                this.user.setRole((String) this.cmb_user_role.getSelectedItem());

                    result = this.userManager.save(this.user);
                }

                if (result) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });

        btn_ara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String role = (String) cmb_filter_role.getSelectedItem();
                ArrayList<Object[]> userList = userManager.getForTable(col_user.length);
                ArrayList<Object[]> filteredList = new ArrayList<>();
                for (Object[] user : userList) {
                    if (user[3].equals(role)) {
                        filteredList.add(user);
                    }
                }
                createTable(tmdl_user, tbl_user, col_user, filteredList);



            }
        });
        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserTable(null);
            }
        });
    }    // Değerlendirme 7

    public void loadUserTable(ArrayList<Object[]> UserList) {
        this.col_user = new Object[] {"ID", "Kullanıcı Adı", "Parola", "Rol"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length);
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    } // Kullanıcı tablosu

    private void loadUserComponent() {
        tableSelectedRow(this.tbl_user);

        this.user_menu = new JPopupMenu();

        this.user_menu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(this.tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(this.tbl_user, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_user.setComponentPopupMenu(user_menu);

    }

}
