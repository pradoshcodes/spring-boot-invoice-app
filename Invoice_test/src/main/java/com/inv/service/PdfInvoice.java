package com.inv.service;




import com.inv.entity.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

@Service
public class PdfInvoice {

    public static byte[] generateInvoicePdf(Invoice invoice) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("INVOICE"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Customer: " + invoice.getCustomerName()));
            document.add(new Paragraph("Product: " + invoice.getProductName()));
            document.add(new Paragraph("Quantity: " + invoice.getQuantity()));
            document.add(new Paragraph("Price: " + invoice.getPrice()));
            document.add(new Paragraph("Total Amount: " + invoice.getTotalAmount()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}

