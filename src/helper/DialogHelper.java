/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author duann
 */
public class DialogHelper {

//    hiển thị thông báo cho người dùng
    public static void alert(Component parent, String messager) {
        JOptionPane.showMessageDialog(parent, messager, "Hệ thống quản lý quán coffee", JOptionPane.INFORMATION_MESSAGE);
    }

//    Hiển thị thông báo và yêu cầu người dùng xác nhận
    public static boolean confirm(Component parent, String messager) {
        int result = JOptionPane.showConfirmDialog(parent, messager, "Hệ thống quản lý quán coffee", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

//    hiển thị thông báo yêu cầu nhập dữ liệu;
    public static String prompt(Component parent, String messager) {
        return JOptionPane.showInputDialog(parent, messager, "Hệ thống quản lý quán coffee", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void setInfinity(JLabel label, String text) {
        label.setVisible(true);
        label.setForeground(new Color(86, 188, 27));
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setText(text);

        new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                label.setVisible(false);
            }
        }).start();
    }

}
