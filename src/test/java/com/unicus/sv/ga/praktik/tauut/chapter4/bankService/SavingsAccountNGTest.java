package com.unicus.sv.ga.praktik.tauut.chapter4.bankService;

import com.unicus.sv.ga.praktik.tauut.chapter4.bankService.exceptions.*;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class SavingsAccountNGTest {

    Customer customer;
    SavingsAccount savings;

    @BeforeClass
    public void beforeClass() {
        customer = new Customer("Rose Abrams", "Sweden", "roseabrams@gmail.com");
    }

    @BeforeMethod
    public void beforeMethod() {
        savings = new SavingsAccount(customer, 100, 123456789);
    }

    @Test
    public void withdrawValid() throws InsufficientFundsException {
        savings.withdraw(60);
        assertEquals(savings.getBalance(), 40);
    }

    @Test//(expectedExceptions = InsufficientFundsException.class)
    public void withdrawInvalid() throws InsufficientFundsException {
        try {
            savings.withdraw(200);
            fail("Expected InsufficientFundsException, actually no exeption");
        } catch (InsufficientFundsException e) {
            assertEquals(savings.getBalance(), 100);
        }
    }
}
