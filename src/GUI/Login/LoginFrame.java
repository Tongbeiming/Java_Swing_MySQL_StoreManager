package GUI.Login;

import DBconnection.DbUtil;
import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() throws SQLException {
        setTitle("登录");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background image
        ImageIcon backgroundIcon = new ImageIcon("src/GUI/Login/img.png");
        Image img = backgroundIcon.getImage();
        backgroundIcon.setImage(img.getScaledInstance(400, 250, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(new GridBagLayout());
        this.setContentPane(background);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        // Username panel
        JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(false);
        usernamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        background.add(usernamePanel, gbc);

        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("宋体", Font.BOLD, 16));
        usernamePanel.add(usernameLabel);

        usernameField = new JTextField(23);
        usernameField.setFont(new Font("宋体", Font.BOLD, 16));
        usernamePanel.add(usernameField);

        // Password panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(false);
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        background.add(passwordPanel, gbc);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new Font("宋体", Font.BOLD, 16));
        passwordPanel.add(passwordLabel);

        passwordField = new JPasswordField(25);
        passwordField.setFont(new Font("宋体", Font.BOLD, 16));
        passwordPanel.add(passwordField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        loginButton = new JButton("登录");
        loginButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(loginButton);

        registerButton = new JButton("注册");
        registerButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(registerButton);

        background.add(buttonPanel, gbc);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (checkCredentials(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MainFrame();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationDialog registrationDialog = new RegistrationDialog(LoginFrame.this);
                registrationDialog.setVisible(true);
            }
        });
    }

        private boolean checkCredentials(String username,String password) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DbUtil.getConnection();
                String query = "SELECT * FROM user WHERE username = ? AND password = ?";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);

                rs = stmt.executeQuery();

                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Error checking credentials", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        /* ignored */}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        /* ignored */}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        /* ignored */}
                }
            }
        }
}