/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Address;
import model.Customer;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    private static final String PERSISTENCE_UNIT_NAME = "EntityManagerDemoPU";
    public static void main(String[] args) {
        createData();
        printAllCustomer();
        printCustomerByCity("Bangkok");
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
        public static void createData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            Customer customer1 = new Customer(1L, "John", "Henry", "jh@mail.com");
            Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH");
            customer1.setAddressId(address1);
            address1.setCustomerFk(customer1);
            em.persist(customer1);
            em.persist(address1);

            Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com");
            Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
            customer2.setAddressId(address2);
            address2.setCustomerFk(customer2);
            em.persist(customer2);
            em.persist(address2);

            Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com");
            Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
            customer3.setAddressId(address3);
            address3.setCustomerFk(customer3);
            em.persist(customer3);
            em.persist(address3);

            Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");
            Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");
            customer4.setAddressId(address4);
            address4.setCustomerFk(customer4);
            em.persist(customer4);
            em.persist(address4);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void printAllCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        try {
            List<Customer> customerList = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
            for (Customer customer : customerList) {
                printCustomerDetails(customer);
            }
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public static void printCustomerByCity(String city) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Address.findByCity");
            query.setParameter("city", city);
            List<Address> addressList = query.getResultList();
            for (Address address : addressList) {
                printCustomerDetails(address.getCustomerFk());
            }
        } finally {
            em.close();
            emf.close();
        }
    }
    
     private static void printCustomerDetails(Customer customer) {
        System.out.println("First Name: " + customer.getFirstname());
        System.out.println("Last Name: " + customer.getLastname());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Street: " + customer.getAddressId().getStreet());
        System.out.println("City: " + customer.getAddressId().getCity());
        System.out.println("Country: " + customer.getAddressId().getCountry());
        System.out.println("Zip Code: " + customer.getAddressId().getZipcode());
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
    }

    
}
