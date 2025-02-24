package org.example.Assignment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilterInvoiceTest {
    @Test
    public void filterInvoiceTest() {
        // Given: A real database instance and DAO
        Database db = new Database();  // Create real Database object
        QueryInvoicesDAO dao = new QueryInvoicesDAO(db);  // Pass real Database to DAO
        FilterInvoice filterInvoice = new FilterInvoice(dao);  // Pass DAO to FilterInvoice

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
        // Given: A mock database and a stubbed DAO
        QueryInvoicesDAO mockDao = mock(QueryInvoicesDAO.class);
        List<Invoice> stubbedInvoices = Arrays.asList(
                new Invoice("INV001", 50),  // Low value invoice
                new Invoice("INV002", 30),  // Low value invoice
                new Invoice("INV003", 200)  // High value invoice (should be filtered out)
        );

        // Stub the DAO to return a controlled list of invoices
        when(mockDao.all()).thenReturn(stubbedInvoices);

        // Inject mock DAO into FilterInvoice
        FilterInvoice filterInvoice = new FilterInvoice(mockDao);

        // When: Calling lowValueInvoices method
        List<Invoice> result = filterInvoice.lowValueInvoices();

        // Then: Verify that only invoices with value < 100 are returned
        assertEquals(2, result.size(), "Only two invoices should be in the filtered list");
        assertEquals(50, result.get(0).getValue(), "First invoice should have value 50");
        assertEquals(30, result.get(1).getValue(), "Second invoice should have value 30");
    }

}
