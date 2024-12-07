/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rentalcarapp;

/**
 *
 * @author HUAWEI
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminDashboard {
    private JFrame adminFrame;
    private ArrayList<Vehicle> vehicleInventory;
    private ArrayList<Rental> rentalHistory;

    public AdminDashboard() {
        vehicleInventory = new ArrayList<>();
        rentalHistory = new ArrayList<>();
        setupAdminDashboard();
    }

    private void setupAdminDashboard() {
        adminFrame = new JFrame("Admin Dashboard");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setSize(800, 600);
        adminFrame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Vehicle Inventory Tab
        JPanel inventoryPanel = createInventoryPanel();
        tabbedPane.add("Vehicle Inventory", inventoryPanel);

        // Rental History Tab
        JPanel historyPanel = createHistoryPanel();
        tabbedPane.add("Rental History", historyPanel);

        adminFrame.add(tabbedPane);
        adminFrame.setVisible(true);
    }

    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table for displaying inventory
        String[] columnNames = {"Vehicle ID", "Make", "Model", "Year", "Availability"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons for inventory management
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Vehicle");
        JButton editButton = new JButton("Edit Vehicle");
        JButton deleteButton = new JButton("Delete Vehicle");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add button functionality
        addButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(adminFrame, "Enter Vehicle ID:");
            String make = JOptionPane.showInputDialog(adminFrame, "Enter Make:");
            String model = JOptionPane.showInputDialog(adminFrame, "Enter Model:");
            String year = JOptionPane.showInputDialog(adminFrame, "Enter Year:");
            vehicleInventory.add(new Vehicle(id, make, model, year, true));
            tableModel.addRow(new Object[]{id, make, model, year, "Available"});
        });

        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table for displaying rental history
        String[] columnNames = {"Rental ID", "Customer", "Vehicle", "Date", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }
}

class Vehicle {
    private String id, make, model, year;
    private boolean available;

    public Vehicle(String id, String make, String model, String year, boolean available) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.available = available;
    }

    // Getters and setters can be added as needed
}

class Rental {
    private String rentalId, customer, vehicle, date, status;

    public Rental(String rentalId, String customer, String vehicle, String date, String status) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.date = date;
        this.status = status;
    }

    // Getters and setters can be added as needed
}


