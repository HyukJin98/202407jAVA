package thread3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewDept extends JFrame {
    JTextField tf = new JTextField(20);
    JButton bt = new JButton("조회");
    JTextArea ta = new JTextArea(10, 40);
    Connection conn;
    Statement stmt;
    JFrame jf;

    ViewDept() {
        jf = this;
        String URL = "jdbc:mysql://localhost:3307/spring5fs";    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, "root", "mysql");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e2) {
            e2.printStackTrace();
        }
        
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        
        JPanel jp1 = new JPanel(new FlowLayout());
        jp1.add(tf); jp1.add(bt);
        con.add(jp1, BorderLayout.NORTH);
        
        JPanel jp2 = new JPanel();
        jp2.setLayout(new FlowLayout());
        jp2.add(ta);
        con.add(jp2, BorderLayout.CENTER);
        
        con.add(new WinTime(), BorderLayout.SOUTH);
        
        this.setTitle("view dept 테이블");
        this.setBounds(1200, 200, 500, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = tf.getText();
                String sql = String.format("select deptno, dname, loc from dept where loc like '%%%s%%'", input);
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    ta.setText("");
                    boolean flag = true;
                    while (rs.next()) {
                        flag = false;
                        int deptno = rs.getInt("deptno");
                        String dname = rs.getString("dname");
                        String loc = rs.getString("loc");
                        ta.append(String.format("%d %s %s\n", deptno, dname, loc));
                    }
                    if (flag) {
                        JOptionPane.showMessageDialog(jf, "해당 자료없습니다.", "정보", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] ar) {
        new ViewDept();
    }

    class WinTime extends JPanel implements Runnable {
        JLabel lb1 = new JLabel();

        WinTime() {
            this.add(lb1);
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            for (;;) {
                LocalTime localTime = LocalTime.now();
                String str = String.format("%02d:%02d:%02d", 
                         localTime.getHour(), localTime.getMinute(), localTime.getSecond());
                lb1.setText(str);
                try {
                    Thread.sleep(1000);  // 1초 sleep
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}