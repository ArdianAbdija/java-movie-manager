package hr.algebra.view;

import hr.algebra.auth.AuthService;
import hr.algebra.model.User;
import hr.algebra.utilities.MessageUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginDialog extends JDialog {

    private final JTextField tfUsername = new JTextField(20);
    private final JPasswordField pfPassword = new JPasswordField(20);
    private final JButton btnLogin = new JButton("login");
    private final JButton btnCancel = new JButton("cancel");
    private final JButton btnRegister = new JButton("register");


    private Optional<User> loggedInUser = Optional.empty();

    public LoginDialog(Frame owner) {
        super(owner, "login", true);
        init();
    }

    public Optional<User> getLoggedInUser() {
        return loggedInUser;
    }

    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.add(new JLabel("username:"));
        form.add(tfUsername);
        form.add(new JLabel("password:"));
        form.add(pfPassword);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnRegister);
        buttons.add(btnLogin);
        buttons.add(btnCancel);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        
        btnRegister.addActionListener(e -> openRegister());
        btnLogin.addActionListener(e -> login());
        btnCancel.addActionListener(e -> dispose());

        getRootPane().setDefaultButton(btnLogin);

        pack();
        setLocationRelativeTo(getOwner());
    }
    
    private void openRegister() {
    RegisterDialog rd = new RegisterDialog((java.awt.Frame) getOwner());
    rd.setVisible(true);
}


    private void login() {
        String username = tfUsername.getText().trim();
        char[] password = pfPassword.getPassword();

        if (username.isEmpty() || password.length == 0) {
            MessageUtils.showErrorMessage("login", "enter username and password");
            return;
        }

        try {
            AuthService auth = new AuthService();
            loggedInUser = auth.login(username, password);

            if (loggedInUser.isPresent()) {
                dispose();
            } else {
                MessageUtils.showErrorMessage("login", "invalid credentials");
                pfPassword.setText("");
            }

        } catch (Exception ex) {
            MessageUtils.showErrorMessage("error", "login failed: " + ex.getMessage());
        }
    }
}
