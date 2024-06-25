package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    // tema
    public static void setTheme() {
        // LayoutName !
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }


    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    // oto pop up -> dil değişimi
    public static void optionPane() {
        UIManager.put("OptionPane.okButtonText","Okey");
        UIManager.put("OptionPane.yesButtonText","Yes");
        UIManager.put("OptionPane.noButtonText","No");
    }


    // TODO : mesaj gösterme !
    public static void showMsg(String str) {
        optionPane();

        String msg;
        String title;

        switch (str) {
            case "fill" -> {
                msg = "Please fill in all fields ! ";
                title = "Error!";
            }

            case "done" -> {
                msg = "Transaction Successful !";
                title = "Success";
            }
            case "notFound" -> {
                msg = "No records found !";
                title = "error !";
            }

            case "error" -> {
                msg = " Sorry,an error has ocured !";
                title = "Error ! ";
            }
            default -> {
                msg = str;
                title = "Message";
            }

        }

        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean showMassageSure(String messageType) {
        optionPane();
        String message;

        switch (messageType) {
            case "sure":
                message = "Are You Sure You Want To Perform This Operation?";
                break;
            default:
                message = messageType;
        }

        // bilgi mesajı
        return JOptionPane.showConfirmDialog(null, message, "Warning !!!", JOptionPane.YES_NO_OPTION) == 0;
    }




    public static boolean isFieldListEmpty(JTextField[] fields) {
        for (JTextField field : fields) {
            if (isFieldEmpty(field)) return true;
        }

        return false;
    }

    // textField içeriği boş mu değil mi ?
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }


}
