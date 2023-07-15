package tiket;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Login extends javax.swing.JFrame {

    Connection con;
    Statement st;

    public Login() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // Menempatkan frame di tengah layar setelah komponen diatur
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        login = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("src/tiket/kereta.jpg")); // Path to your background image file
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        btnlogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 24)); // Set the font and style
        jLabel1.setForeground(new java.awt.Color(0, 0, 0)); // Set the font color
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 24)); // Set the font and style
        jLabel2.setForeground(new java.awt.Color(0, 0, 0)); // Set the font color
        jLabel2.setText("Password");

        username.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 24)); // Set the font and style

        password.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 24)); // Set the font and style

        btnlogin.setBackground(new java.awt.Color(65, 105, 255));
        btnlogin.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 24)); // Set the font and style
        btnlogin.setForeground(new java.awt.Color(0, 0, 0));
        btnlogin.setText("LOGIN");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 60)); // Set the font and style
        jLabel3.setForeground(new java.awt.Color(0, 0, 0)); // Set the font color
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jLabel3.setText("Kereta_Sam");

        login.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        login.add(jLabel3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        login.add(jLabel1, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL; // Menentukan pengisian horizontal
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Memanjangkan hingga 2 kolom
        login.add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        login.add(jLabel2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Memanjangkan hingga 2 kolom
        login.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        login.add(btnlogin, gbc);

        getContentPane().add(login, BorderLayout.CENTER);

        pack();
    }

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String pass = new String(password.getPassword());
            con = Koneksi.getKoneksi();
            String sql = "SELECT * FROM admin WHERE username='" + username.getText() + "' And password='" + pass + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (username.getText().equals(rs.getString("username")) && pass.equals(rs.getString("password"))) {
                    new Home().setVisible(true);
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Gagal woy gagal");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel login;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
}