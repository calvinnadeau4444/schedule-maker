import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ShiftSchedulerUI extends JFrame {
    private DefaultListModel<String> shiftListModel;
    private JList<String> shiftList;
    private JTextField dayField, startField, endField;
    private JCheckBox lockCheck;

    public ShiftSchedulerUI() {
        setTitle("Monthly Shift Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        shiftListModel = new DefaultListModel<>();
        shiftList = new JList<>(shiftListModel);
        JScrollPane scrollPane = new JScrollPane(shiftList);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Day:"));
        dayField = new JTextField();
        inputPanel.add(dayField);
        inputPanel.add(new JLabel("Start Time (HH:MM):"));
        startField = new JTextField();
        inputPanel.add(startField);
        inputPanel.add(new JLabel("End Time (HH:MM):"));
        endField = new JTextField();
        inputPanel.add(endField);
        lockCheck = new JCheckBox("Lock Shift");
        inputPanel.add(lockCheck);

        JButton addShiftBtn = new JButton("Add Shift");
        addShiftBtn.addActionListener(e -> addShift());
        inputPanel.add(addShiftBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addShift() {
        String day = dayField.getText();
        String start = startField.getText();
        String end = endField.getText();
        boolean isLocked = lockCheck.isSelected();

        if (!day.isEmpty() && !start.isEmpty() && !end.isEmpty()) {
            String shift = day + ": " + start + " - " + end + (isLocked ? " [LOCKED]" : "");
            shiftListModel.addElement(shift);
            dayField.setText("");
            startField.setText("");
            endField.setText("");
            lockCheck.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShiftSchedulerUI::new);
    }
}