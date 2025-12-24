package com.inv.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inv.entity.Invoice;
import com.inv.service.CsvInvoice;
import com.inv.service.InvoiceService;
import com.inv.service.PdfInvoice;

import jakarta.servlet.http.HttpSession;

@Controller
public class InvoiceController {

	@Autowired
	private PdfInvoice pdfinvoice;
	
	
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public String showForm() {
        return "invoice-form";
    }

    @PostMapping("/generate-invoice")
    public String generateInvoice(
            @RequestParam String customerName,
            @RequestParam String productName,
            @RequestParam int quantity,
            @RequestParam double price,
            Model model) {

        Invoice invoice = new Invoice();
        invoice.setCustomerName(customerName);
        invoice.setProductName(productName);
        invoice.setQuantity(quantity);
        invoice.setPrice(price);

        invoiceService.calculateTotal(invoice);

        model.addAttribute("invoice", invoice);
        return "invoice-view";
    }
    
    
    @GetMapping("/invoice/pdf")
    public ResponseEntity<byte[]> downloadPdf(
            @RequestParam String customerName,
            @RequestParam String productName,
            @RequestParam int quantity,
            @RequestParam double price) {

        Invoice invoice = new Invoice();
        invoice.setCustomerName(customerName);
        invoice.setProductName(productName);
        invoice.setQuantity(quantity);
        invoice.setPrice(price);
        invoice.setTotalAmount(quantity * price);

        byte[] pdf = PdfInvoice.generateInvoicePdf(invoice);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=invoice.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdf);
    }
    
    
    @GetMapping("/invoice/csv")
    public ResponseEntity<String> downloadCsv(
            @RequestParam String customerName,
            @RequestParam String productName,
            @RequestParam int quantity,
            @RequestParam double price) {

        Invoice invoice = new Invoice();
        invoice.setCustomerName(customerName);
        invoice.setProductName(productName);
        invoice.setQuantity(quantity);
        invoice.setPrice(price);
        invoice.setTotalAmount(quantity * price);

        String csv = CsvInvoice.generateInvoiceCsv(invoice);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=invoice.csv")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(csv);
    }


    
    
//    @GetMapping("/invoice/csv")
//    public ResponseEntity<String> downloadCsv(HttpSession session) {
//
//        Invoice invoice = (Invoice) session.getAttribute("invoice");
//
//        String csv = CsvInvoice.generateInvoiceCsv(invoice);
//
//        return ResponseEntity.ok()
//                .header("Content-Disposition", "attachment; filename=invoice.csv")
//                .body(csv);
//    }


}

