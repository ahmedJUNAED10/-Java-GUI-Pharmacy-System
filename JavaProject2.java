import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Pharmecy {
    private String medicineName;
    private String serialNum;
    private double price;
    private int numberofMedicine;

    public Pharmecy(String name, String serial, double price, int qty) {
        this.medicineName = name;
        this.serialNum = serial;
        this.price = price;
        this.numberofMedicine = qty;
    }

    String getName(){ return medicineName; }
    String getSerialno(){ return serialNum; }
    double getPrice(){ return price; }
    int getNumberofMedicine(){ return numberofMedicine; }

    void setName(String name){ this.medicineName = name; }
    void setPrice(double price){ this.price = price; }
    void setQuantity(int qty){ this.numberofMedicine = qty; }
}


public class JavaProject2 extends JFrame implements ActionListener {

    JTextField nameField, serialField, priceField, qtyField;
    JTextArea outputArea;

    Pharmecy[] list = new Pharmecy[50];
    int count = 0;

    public JavaProject2() {

        setTitle("Pharmecy Medicine Management System");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        add(new JLabel("Medicine Name:"));
        nameField = new JTextField(10);
        add(nameField);

        add(new JLabel("Serial No:"));
        serialField = new JTextField(10);
        add(serialField);

        add(new JLabel("Price:"));
        priceField = new JTextField(10);
        add(priceField);

        add(new JLabel("Quantity:"));
        qtyField = new JTextField(10);
        add(qtyField);

        
        String[] btns = {"Add", "Search Medicine", "Update", "Delete", "Display"};

        for (String b : btns) {
            JButton btn = new JButton(b);

            if (b.equals("Add"))
                btn.setBackground(new Color(0,153,76));
            else if (b.equals("Delete"))
                btn.setBackground(Color.MAGENTA);
            else if (b.equals("Update"))
                btn.setBackground(Color.GREEN);
            else if (b.equals("Search Medicine"))
                btn.setBackground(Color.CYAN);
            else
                btn.setBackground(Color.LIGHT_GRAY);

            btn.setOpaque(true);
            btn.setBorderPainted(false);

            btn.addActionListener(this);
            add(btn);
        }

       
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        String name = nameField.getText();
        String serial = serialField.getText();
        double price = 0;
        int qty = 0;

        try {
            if (!priceField.getText().isEmpty())
                price = Double.parseDouble(priceField.getText());

            if (!qtyField.getText().isEmpty())
                qty = Integer.parseInt(qtyField.getText());

        } catch(Exception ex) {
            outputArea.setText("Invalid Input!");
            return;
        }

     
        if (cmd.equals("Add")) {
            if (count < list.length) {
                list[count] = new Pharmecy(name, serial, price, qty);
                count++;
                outputArea.setText("Medicine Added");
            } else {
                outputArea.setText("List Full");
            }
        }

       
        else if (cmd.equals("Search")) {
            boolean found = false;

            for (int i = 0; i < count; i++) {
                if (list[i].getSerialno().equals(serial)) {
                    outputArea.setText("Found: " + list[i].getName()
                            + " | Price: " + list[i].getPrice()
                            + " | Qty: " + list[i].getNumberofMedicine());
                    found = true;
                    break;
                }
            }

            if (!found)
                outputArea.setText("Not Found");
        }

        
        else if (cmd.equals("Update")) {
            boolean found = false;

            for (int i = 0; i < count; i++) {
                if (list[i].getSerialno().equals(serial)) {
                    list[i].setName(name);
                    list[i].setPrice(price);
                    list[i].setQuantity(qty);
                    outputArea.setText("Updated");
                    found = true;
                    break;
                }
            }

            if (!found)
                outputArea.setText("Not Found");
        }

        else if (cmd.equals("Delete")) {
            boolean found = false;

            for (int i = 0; i < count; i++) {
                if (list[i].getSerialno().equals(serial)) {

                    for (int j = i; j < count - 1; j++) {
                        list[j] = list[j + 1];
                    }

                    count--;
                    outputArea.setText("Deleted");
                    found = true;
                    break;
                }
            }

            if (!found)
                outputArea.setText("Not Found");
        }

        
        else if (cmd.equals("Display")) {
            String data = "";

            for (int i = 0; i < count; i++) {
                data += "Name: " + list[i].getName() +
                        ", Serial: " + list[i].getSerialno() +
                        ", Price: " + list[i].getPrice() +
                        ", Qty: " + list[i].getNumberofMedicine() + "\n";
            }

            outputArea.setText(data);
        }
    }

    public static void main(String[] args) {
        new JavaProject2();
    }
}
