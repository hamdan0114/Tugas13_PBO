import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField num1Field;
    private JTextField num2Field;
    private JLabel resultLabel;
    private JButton addButton, subtractButton, multiplyButton, divideButton, modulusButton;

    public SimpleCalculator() {
        setTitle("Tugas Pertemuan ke-13: Kalkulator Sederhana");
        setSize(400, 300); // Adjust size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using null layout for absolute positioning (simpler for this example, but consider Layout Managers for complex UIs)

        // Initialize components
        num1Field = new JTextField();
        num2Field = new JTextField();
        resultLabel = new JLabel("Hasil");
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        modulusButton = new JButton("Modulus");

        // Set bounds (x, y, width, height) - you'll need to adjust these for proper layout
        num1Field.setBounds(50, 50, 100, 30);
        num2Field.setBounds(160, 50, 100, 30);
        resultLabel.setBounds(280, 50, 100, 30); // " = Hasil" part
        
        // Buttons (adjust positions to match your desired layout from the image)
        addButton.setBounds(50, 100, 80, 30);
        subtractButton.setBounds(50, 140, 80, 30);
        multiplyButton.setBounds(50, 180, 80, 30);
        divideButton.setBounds(50, 220, 80, 30);
        modulusButton.setBounds(50, 260, 80, 30);


        // Add components to the frame
        add(num1Field);
        add(num2Field);
        add(resultLabel);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(modulusButton);

        // Register ActionListeners for buttons
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        modulusButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = 0;

            if (e.getSource() == addButton) {
                result = num1 + num2;
            } else if (e.getSource() == subtractButton) {
                result = num1 - num2;
            } else if (e.getSource() == multiplyButton) {
                result = num1 * num2;
            } else if (e.getSource() == divideButton) {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    resultLabel.setText("Error: Div by 0");
                    return; // Exit if division by zero
                }
            } else if (e.getSource() == modulusButton) {
                 if (num2 != 0) {
                    result = num1 % num2;
                 } else {
                    resultLabel.setText("Error: Mod by 0");
                    return; // Exit if modulus by zero
                 }
            }
            resultLabel.setText("Hasil: " + result);

        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator());
    }
}