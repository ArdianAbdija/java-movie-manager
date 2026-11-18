package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.User;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.utilities.PasswordHasher;

import javax.swing.*;
import java.awt.*;

public class RegisterDialog extends JDialog {

    private final JTextField tfUsername = new JTextField(20);
    private final JPasswordField pfPassword = new JPasswordField(20);
    private final JPasswordField pfConfirm = new JPasswordField(20);

    private final JButton btnRegister = new JButton("register");
    private final JButton btnCancel = new JButton("cancel");

    public RegisterDialog(Frame owner) {
        super(owner, "register", true);
        init();
    }

    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.add(new JLabel("username:"));
        form.add(tfUsername);
        form.add(new JLabel("password:"));
        form.add(pfPassword);
        form.add(new JLabel("confirm password:"));
        form.add(pfConfirm);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnRegister);
        buttons.add(btnCancel);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        btnRegister.addActionListener(e -> register());
        btnCancel.addActionListener(e -> dispose());

        getRootPane().setDefaultButton(btnRegister);

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void register() {
        String username = tfUsername.getText().trim();
        char[] password = pfPassword.getPassword();
        char[] confirm = pfConfirm.getPassword();

        if (username.isEmpty() || password.length == 0 || confirm.length == 0) {
            MessageUtils.showErrorMessage("register", "all fields are required");
            return;
        }

        if (!java.util.Arrays.equals(password, confirm)) {
            MessageUtils.showErrorMessage("register", "passwords do not match");
            pfPassword.setText("");
            pfConfirm.setText("");
            return;
        }

        try {
            Repository repo = RepositoryFactory.getRepository();

            String hash = PasswordHasher.hash(password);
            User user = new User(username, hash, "user");

            repo.createUser(user);

            MessageUtils.showInformationMessage("register", "account created. you can log in now.");
            dispose();

        } catch (Exception ex) {
            // common case: username unique constraint
            MessageUtils.showErrorMessage("register", "registration failed: " + ex.getMessage());
        }
    }
}
