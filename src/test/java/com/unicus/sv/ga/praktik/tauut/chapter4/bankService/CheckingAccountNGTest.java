package com.unicus.sv.ga.praktik.tauut.chapter4.bankService;

import com.unicus.sv.ga.praktik.tauut.chapter4.notificationService.EmailService;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class CheckingAccountNGTest {

    Customer customer;
    CheckingAccount checking;

    @BeforeClass
    public void beforeClass() {
        customer = new Customer("Rose Abrams", "Sweden", "roseabrams@gmail.com");
    }

    @BeforeMethod
    public void beforeMethod() {
        checking = new CheckingAccount(customer, 250, 123456789);
        MockitoAnnotations.openMocks(this);
    }
    
    @Mock
    Check mockCheck;
    
    @Test
    public void processCheck() {
        when(mockCheck.getAmount()).thenReturn(100d);
        when(mockCheck.getCheckNumber()).thenReturn(4321);
        checking.processCheck(mockCheck);
        assertEquals(checking.getBalance(), 150d);
    }
    
    @Spy
    EmailService spiedService = new EmailService();
    
    @Test
    public void checkNotice() {
        checking.enableNotifications(spiedService);
        when(mockCheck.getCheckNumber()).thenReturn(4321);
        when(mockCheck.getAmount()).thenReturn(100d);
        checking.processCheck(mockCheck);
        assertEquals(checking.getBalance(), 150d);
        verify(spiedService).notify(contains("Check #4321"), eq(checking.getOwner().getEmail()));
        assertEquals(checking.getBalance(), 150d);
    }
}
