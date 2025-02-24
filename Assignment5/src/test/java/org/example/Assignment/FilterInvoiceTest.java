package org.example.Assignment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilterInvoiceTest {
    @Test
    public void filterInvoiceTest() {
        // Given: A real database and DAO instance
        FilterInvoice filterInvoice = new FilterInvoice();

        // When: Calling lowValueInvoices method
        List<Invoice> result = filterInvoice.lowValueInvoices();

        // Then: Verify that only invoices with value < 100 are returned
        assertNotNull(result, "The returned list should not be null");
        for (Invoice invoice : result) {
            assertTrue(invoice.getValue() < 100, "Invoice value should be less than 100");
        }
    }
    @Test
    void filterInvoiceStubbedTest(){

    }
    /// Part 1 up , part 2 down ////////////
    @Test
    void sendLowValuedInvoices(){

    }
    @Test
    void testWhenLowInvoicesSent (){

    }
    @Test
    void testWhenNoInvoices () {

    }
    /// Part 3 down//////////////////
    @Test
    void part3(){

    }

}