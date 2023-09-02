package org.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomerPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

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
            em.persist(customer);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
