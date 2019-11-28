/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.NhanVien;

/**
 *
 * @author duann
 */
public class ShareHelper {

//    ảnh biểu tượng ứng dụng xuất hiện trên mọi cửa sổ
    public static final Image APP_ICON;

    static {
//        tải biểu tượng ứng dụng
        String file = "/images/icons8-cafe-80.png";
        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
    }

//    đối tượng chứa thông tin của người sử dụng sau khi đăng nhập
    public static NhanVien USER = null;

//    Xóa thông tin của người sử dụng khi có yêu cầu đăng suất
    public static void logOff() {
        ShareHelper.USER = null;
    }

//    Kiểm tra xem đăng nhập hay chưa
    public static boolean authenticated() {
        return ShareHelper.USER != null;
    }

//    Set Table 
    public static void DrawTable(JTable tblGridView) {
        tblGridView.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tblGridView.getTableHeader().setForeground(Color.blue);

        // Column Width
        tblGridView.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblGridView.setForeground(Color.black);

        // Row Height
        tblGridView.setRowHeight(20);
    }

    public static void setBoderForTable(JScrollPane scp) {
        scp.setViewportBorder(null);
        scp.setBorder(null);
    }

//    quản lý là false
    public static boolean getQuyenTruyCap() {
        if (USER.isVaiTro() == false) {
            return true;
        }
        return false;
    }

}
