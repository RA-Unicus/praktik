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

    @Test(dataProvider = "ValidData")
    public void withdrawValid(double amount, double expectedRemaining) throws InsufficientFundsException {
        savings.withdraw(amount);
        assertEquals(savings.getBalance(), expectedRemaining);
    }
    
    @DataProvider(name = "ValidData")
    private Object[][] validData() {
        return new Object[][] {
            {60d, 40d},
            {100d, 0d}
        };
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
