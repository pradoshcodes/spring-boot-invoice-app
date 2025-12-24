package com.inv.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.inv.entity.Invoice;

@Service
public class InvoiceService {

    public void calculateTotal(Invoice invoice) {

//        invoice.setTotalAmount(total);
        DecimalFormat df = new DecimalFormat("#.00");
        double total = invoice.getQuantity() * invoice.getPrice();
        invoice.setTotalAmount(Double.parseDouble(df.format(total)));

    }
}