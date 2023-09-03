package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String[][] customerData = {
                {"Mave", "Dickenson", "exaplemail1@mail.com", "+38066789694"},
                {"John", "Smith", "exaplemail2@mail.com", "+38066785678"},
                {"Alice", "Johnson", "exaplemail3@mail.com", "+38066781234"}
                // Add more customer data as needed
        };

        // Create an array to store Customer objects
        org.example.Customer[] customers = new org.example.Customer[customerData.length];

        // Populate the array with Customer objects
        for (int i = 0; i < customerData.length; i++) {
            customers[i] = new org.example.Customer(
                    customerData[i][0], // First Name
                    customerData[i][1], // Last Name
                    customerData[i][2], // Email
                    customerData[i][3] // Phone Number
            );
        }

        // Print the generated customers
        for (org.example.Customer customer : customers) {
            session.persist(customer);
        }

        transaction.commit();

        session.close();
        sessionFactory.close();
    }
}
