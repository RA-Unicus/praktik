package com.unicus.sv.ga.praktik.tauut.chapter4.bankService;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class CustomerNGTest {

    @Test
    public void createCustomer() {
        Customer customer = new Customer("Rose Abrams", "Sweden", "roseabrams@gmail.com");
        assertNotNull(customer);
        assertEquals(customer.getName(), "Rose Abrams");
        assertEquals(customer.getAddress(), "Sweden");
        assertEquals(customer.getEmail(), "roseabrams@gmail.com");
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer("Rose Abrams", "Sweden", "roseabrams@gmail.com");
        customer.updateName("Jennifer Abrams");
        customer.updateAddress("Norway");
        customer.updateEmail("jennyabrams@gmail.com");
        assertEquals(customer.getName(), "Jennifer Abrams");
        assertEquals(customer.getAddress(), "Norway");
        assertEquals(customer.getEmail(), "jennyabrams@gmail.com");
    }
}
