package org.example.Assignment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SAP_BasedInvoiceSenderTest {
    @Test
    public void testWhenLowInvoicesSent() {
        // Given: Mock dependencies
        FilterInvoice mockFilter = mock(FilterInvoice.class);
        SAP mockSAP = mock(SAP.class);

        // Stub filter to return low-value invoices
        List<Invoice> lowValueInvoices = Arrays.asList(
                new Invoice("INV001", 50),
                new Invoice("INV002", 30)
        );
        when(mockFilter.lowValueInvoices()).thenReturn(lowValueInvoices);

        // Inject mocks into SAP_BasedInvoiceSender
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(mockFilter, mockSAP);

        // When: Calling sendLowValuedInvoices
        sender.sendLowValuedInvoices();

        // Then: Verify that each invoice was sent once
        verify(mockSAP, times(1)).send(lowValueInvoices.get(0));
        verify(mockSAP, times(1)).send(lowValueInvoices.get(1));

        // Verify that no other interactions happened
        verifyNoMoreInteractions(mockSAP);
    }

    @Test
    public void testWhenNoInvoices() {
        // Given: Mock dependencies
        FilterInvoice mockFilter = mock(FilterInvoice.class);
        SAP mockSAP = mock(SAP.class);

        // Stub filter to return an empty list
        when(mockFilter.lowValueInvoices()).thenReturn(Arrays.asList());

        // Inject mocks into SAP_BasedInvoiceSender
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(mockFilter, mockSAP);

        // When: Calling sendLowValuedInvoices
        sender.sendLowValuedInvoices();

        // Then: Verify that the send method was NEVER called
        verify(mockSAP, never()).send(any(Invoice.class));
    }

}
