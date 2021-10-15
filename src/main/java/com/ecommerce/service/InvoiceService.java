package com.ecommerce.service;

import com.ecommerce.model.Invoice;
import com.ecommerce.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }
}
