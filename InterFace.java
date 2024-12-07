/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rentalcarapp;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class InterFace extends JFrame{

    private ArrayList<String[]> reservations = new ArrayList<>();
    
    public InterFace(){
        setTitle("Display interface");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JLabel welcomeLabel = new JLabel("Welcome to the car rental exhibition", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(welcomeLabel,BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       
        JButton addButton = new JButton("Browse Cars");
        addButton.addActionListener(e -> CarRentalExhibition());
        JButton AddButton = new JButton("Reservations management");
        AddButton.addActionListener(e -> ReservationsManagement());
        buttonPanel.add(addButton);
        buttonPanel.add(AddButton);
        add(buttonPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    private void CarRentalExhibition(){
        JFrame addFrame = new JFrame("Car rental exhibition");
        addFrame.setSize(400,300);
        addFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Car Name", "Price", "Status"};
        String[][] carData = {
            {"Toyota Camry 2025", "$100/day", "Available"},
            {"Honda Accord 2024", "$150/day", "Available"},
            {"Ford Taurus 2025", "$200/day", "Available"}
        };
        
        JTable addTable = new JTable(carData,columnNames);
        JScrollPane scrollPane = new JScrollPane(addTable);
        addFrame.add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> addFrame.dispose());
        JButton ChooseButton = new JButton("Chose");
        ChooseButton.addActionListener(e ->{
            int selectedRow = addTable.getSelectedRow();
            if (selectedRow != -1) {
                String carName = carData[selectedRow][0];
                String price = carData[selectedRow][1];
                CustomerChoice(carName, price, addFrame);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a car first!", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
         
        buttonPanel.add(backButton);
        buttonPanel.add(ChooseButton);
        addFrame.add(buttonPanel,BorderLayout.SOUTH);

        addFrame.setVisible(true);
    }

    private void ReservationsManagement(){
        JFrame addFrame = new JFrame("Reservations management");
        addFrame.setSize(400,300);
        addFrame.setLayout(new BorderLayout());
        if (reservations.isEmpty()) {
            JOptionPane.showMessageDialog(addFrame, "No reservations available!", "Empty List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnNames = {"Car Name", "Price", "Status"};
        String[][] reservationData = reservations.toArray(new String[0][0]);

        JTable reservationTable = new JTable(reservationData, columnNames);
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        addFrame.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> addFrame.dispose());
        addFrame.add(closeButton, BorderLayout.SOUTH);

        addFrame.setVisible(true);
    }

    private void CustomerChoice(String carName, String price, JFrame parentFrame){
        int response = JOptionPane.showConfirmDialog(parentFrame,"Are you sure you want to rent:\n" + carName + " for " + price + "?","Confirm Reservation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            reservations.add(new String[]{carName, price, "Reserved"});
            JOptionPane.showMessageDialog(parentFrame, "Reservation confirmed!");
        }
    }
}