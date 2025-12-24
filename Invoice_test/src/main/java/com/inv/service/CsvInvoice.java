package com.inv.service;

import org.springframework.stereotype.Service;

import com.inv.entity.Invoice;


@Service
public class CsvInvoice {

    public static String generateInvoiceCsv(Invoice invoice) {

        StringBuilder sb = new StringBuilder();

        sb.append("Customer,Product,Quantity,Price,Total\n");
        sb.append(invoice.getCustomerName()).append(",");
        sb.append(invoice.getProductName()).append(",");
        sb.append(invoice.getQuantity()).append(",");
        sb.append(invoice.getPrice()).append(",");
        sb.append(invoice.getTotalAmount());

        return sb.toString();
    }
}
