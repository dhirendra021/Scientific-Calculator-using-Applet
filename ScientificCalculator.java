import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel buttonPanel;
    private String currentOperation = "";
    private double firstOperand = 0;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sin", "cos", "tan", "sqrt",
            "log", "ln", "π", "^",
            "C", "(", ")", "exp"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case "C":
                    display.setText("");
                    firstOperand = 0;
                    currentOperation = "";
                    break;
                case "=":
                    calculate(Double.parseDouble(display.getText()));
                    currentOperation = "";
                    break;
                case "sin":
                case "cos":
                case "tan":
                case "sqrt":
                case "log":
                case "ln":
                case "exp":
                    calculateUnary(command, Double.parseDouble(display.getText()));
                    break;
                case "π":
                    display.setText(String.valueOf(Math.PI));
                    break;
                case "^":
                    firstOperand = Double.parseDouble(display.getText());
                    currentOperation = "^";
                    display.setText("");
                    break;
                default:
                    if ("+-*/".contains(command)) {
                        firstOperand = Double.parseDouble(display.getText());
                        currentOperation = command;
                        display.setText("");
                    } else {
                        display.setText(display.getText() + command);
                    }
            }
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    private void calculate(double secondOperand) {
        double result = 0;
        switch (currentOperation) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                result = firstOperand / secondOperand;
                break;
            case "^":
                result = Math.pow(firstOperand, secondOperand);
                break;
        }
        display.setText(String.valueOf(result));
    }

    private void calculateUnary(String operation, double operand) {
        double result = 0;
        switch (operation) {
            case "sin":
                result = Math.sin(Math.toRadians(operand));
                break;
            case "cos":
                result = Math.cos(Math.toRadians(operand));
                break;
            case "tan":
                result = Math.tan(Math.toRadians(operand));
                break;
            case "sqrt":
                result = Math.sqrt(operand);
                break;
            case "log":
                result = Math.log10(operand);
                break;
            case "ln":
                result = Math.log(operand);
                break;
            case "exp":
                result = Math.exp(operand);
                break;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScientificCalculator().setVisible(true);
        });
    }
}
