import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class RentalCarApp {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RentalCarApp::new);
    }

    public RentalCarApp() {
        createMainPage();
    }

    private void createMainPage() {
        JFrame mainFrame = new JFrame("Main Page");
        setupFrame(mainFrame);

        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("Welcome to the Main Page!"));
        mainFrame.add(contentPanel, BorderLayout.CENTER);

        JButton profileButton = new JButton(new ImageIcon("hero_offroad.jpg"));
        profileButton.setPreferredSize(new Dimension(40, 40));
        profileButton.setFocusPainted(false);
        profileButton.setBorder(BorderFactory.createEmptyBorder());
        profileButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            createAuthPage(mainFrame);
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(profileButton);
        mainFrame.add(topPanel, BorderLayout.NORTH);

        mainFrame.setVisible(true);
    }

    private void createAuthPage(JFrame mainFrame) {
        JFrame authFrame = new JFrame("Auth Page");
        setupFrame(authFrame);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Login or Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField usernameField = createTextField("Username");
        JPasswordField passwordField = createPasswordField("Password");

        centerPanel.add(usernameField);
        centerPanel.add(passwordField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        centerPanel.add(buttonPanel);

        authFrame.add(centerPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            authFrame.dispose();
            mainFrame.setVisible(true);
        });
        authFrame.add(backButton, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(authFrame, "Username or password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(authFrame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                authFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        signupButton.addActionListener(e -> {
            authFrame.dispose();
            createSignupPage(authFrame, mainFrame);
        });

        authFrame.setVisible(true);
    }

    private void createSignupPage(JFrame authFrame, JFrame mainFrame) {
        JFrame signupFrame = new JFrame("Sign Up Page");
        setupFrame(signupFrame);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField emailField = createTextField("Email");
        JPasswordField passwordField = createPasswordField("Password");
        JPasswordField confirmPasswordField = createPasswordField("Confirm Password");

        centerPanel.add(emailField);
        centerPanel.add(passwordField);
        centerPanel.add(confirmPasswordField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton submitButton = new JButton("Sign Up");
        centerPanel.add(submitButton);

        signupFrame.add(centerPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            signupFrame.dispose();
            authFrame.setVisible(true);
        });
        signupFrame.add(backButton, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(signupFrame, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(signupFrame, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (password.length() < 8) {
                JOptionPane.showMessageDialog(signupFrame, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(signupFrame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(signupFrame, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                signupFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        signupFrame.setVisible(true);
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(400, 40));
        textField.setBorder(BorderFactory.createTitledBorder(placeholder));
        return textField;
    }

    private JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(400, 40));
        passwordField.setBorder(BorderFactory.createTitledBorder(placeholder));
        return passwordField;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    private void setupFrame(JFrame frame)
     {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

    }
}
