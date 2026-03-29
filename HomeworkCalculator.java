import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeworkCalculator extends JFrame implements ActionListener {
    private final JTextField display;
    private final StringBuilder input = new StringBuilder();
    private double num1 = 0;
    private char operator = ' ';
    private boolean isOperatorClicked = false;

    public HomeworkCalculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttons = {
                "MC", "MR", "MS", "M+", "M-",
                "CLR", "DEL", "±", "%", "÷",
                "7", "8", "9", "×", "",
                "4", "5", "6", "-", "",
                "1", "2", "3", "+", "",
                "", "0", ".", "="
        };

        for (String text : buttons) {
            if (text.isEmpty()) {
                panel.add(new JLabel());
            } else {
                JButton btn = new JButton(text);
                btn.setFont(new Font("Arial", Font.PLAIN, 18));
                btn.addActionListener(this);
                if (text.matches("[÷×\\-+]|=")) {
                    btn.setBackground(new Color(255, 102, 102));
                    btn.setForeground(Color.WHITE);
                }
                panel.add(btn);
            }
        }
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        double num2 = 0;
        if (command.matches("\\d|\\.")) {
            if (isOperatorClicked) {
                input.setLength(0);
                isOperatorClicked = false;
            }
            input.append(command);
            display.setText(input.toString());
        } else if (command.matches("[÷×\\-+]")) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input.toString());
                operator = command.charAt(0);
                isOperatorClicked = true;
            }
        } else if (command.equals("=")) {
            if (operator != ' ' && !input.isEmpty()) {
                num2 = Double.parseDouble(input.toString());
                double result = switch (operator) {
                    case '+' -> num1 + num2;
                    case '-' -> num1 - num2;
                    case '×' -> num1 * num2;
                    case '÷' -> num2 != 0 ? num1 / num2 : 0;
                    default -> 0;
                };
                display.setText(String.valueOf(result));
                input.setLength(0);
                input.append(result);
                operator = ' ';
                isOperatorClicked = true;
            }
        } else if (command.equals("CLR")) {
            input.setLength(0);
            num1 = num2 = 0;
            operator = ' ';
            display.setText("");
        } else if (command.equals("DEL")) {
            if (!input.isEmpty()) {
                input.deleteCharAt(input.length() - 1);
                display.setText(input.toString());
            }
        } else if (command.equals("±")) {
            if (!input.isEmpty()) {
                double val = Double.parseDouble(input.toString());
                val = -val;
                input.setLength(0);
                input.append(val);
                display.setText(input.toString());
            }
        } else if (command.equals("%")) {
            if (!input.isEmpty()) {
                double val = Double.parseDouble(input.toString());
                val /= 100;
                input.setLength(0);
                input.append(val);
                display.setText(input.toString());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomeworkCalculator().setVisible(true);
        });
    }
}
