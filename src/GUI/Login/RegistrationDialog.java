package GUI.Login;

import DBconnection.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDialog extends JDialog {

    private JTextField usernameField;
    private JTextField trueNameField;
    private JTextField idCardField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;

    public RegistrationDialog(JFrame parent) {
        super(parent, "注册", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Set background image
        ImageIcon backgroundIcon = new ImageIcon("GUI/Login/img.png");
        Image img = backgroundIcon.getImage();
        backgroundIcon.setImage(img.getScaledInstance(400, 300, Image.SCALE_DEFAULT));
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

        usernamePanel.add(new JLabel("用户名:"));
        usernameField = new JTextField(22);
        Font font = new Font("宋体", Font.BOLD, 14);
        usernameField.setFont(font);
        usernamePanel.add(usernameField);

        // True name panel
        JPanel trueNamePanel = new JPanel();
        trueNamePanel.setOpaque(false);
        trueNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        background.add(trueNamePanel, gbc);

        trueNamePanel.add(new JLabel("真实姓名:"));
        trueNameField = new JTextField(20);
        trueNameField.setFont(font);
        trueNamePanel.add(trueNameField);

        // ID card panel
        JPanel idCardPanel = new JPanel();
        idCardPanel.setOpaque(false);
        idCardPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        background.add(idCardPanel, gbc);

        idCardPanel.add(new JLabel("身份证号:"));
        idCardField = new JTextField(20);
        idCardField.setFont(font);
        idCardPanel.add(idCardField);

        // Password panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(false);
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        background.add(passwordPanel, gbc);

        passwordPanel.add(new JLabel("密码:"));
        passwordField = new JPasswordField(24);
        passwordField.setFont(font);
        passwordPanel.add(passwordField);

        // Confirm password panel
        JPanel confirmPasswordPanel = new JPanel();
        confirmPasswordPanel.setOpaque(false);
        confirmPasswordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        background.add(confirmPasswordPanel, gbc);

        confirmPasswordPanel.add(new JLabel("确认密码:"));
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(font);
        confirmPasswordPanel.add(confirmPasswordField);

        // Register button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        registerButton = new JButton("注册");
        buttonPanel.add(registerButton);
        background.add(buttonPanel, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String trueName = trueNameField.getText().trim();
                String idCard = idCardField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(parent, "两次输入的密码不匹配", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        if (register(username, trueName, idCard, password)) {
                            JOptionPane.showMessageDialog(parent, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(parent, "注册失败", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Set font for all labels
        for (Component comp : usernamePanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(font);
            }
        }
        for (Component comp : trueNamePanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(font);
            }
        }
        for (Component comp : idCardPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(font);
            }
        }
        for (Component comp : passwordPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(font);
            }
        }
        for (Component comp : confirmPasswordPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(font);
            }
        }
    }

    public boolean register(String username, String trueName, String idCard, String password) throws SQLException {
        Connection conn = DbUtil.getConnection();
        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO user (username, turename, idcard, password) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, trueName);
            stmt.setString(3, idCard);
            stmt.setString(4, password);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
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