import model.Invoice;
import repository.InvoiceRepository;
import service.InvoiceService;
import service.TicketBuyerService;
import service.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketManagerment {

    private TicketService ticketService = new TicketService();
    private TicketBuyerService ticketBuyerService = new TicketBuyerService();
    private InvoiceService invoiceService = new InvoiceService();
    private List<Invoice> invoices;

    public void addListBuyer() {
        Scanner scanner = new Scanner(System.in);
        int count = 1;
        String exits;
        Boolean check = true;
        while (check) {
            System.out.println("Nhập người mua thứ: " + count);
            ticketBuyerService.addBuyer();
            count++;
            System.out.println("Bạn có muốn nhập người mua tiếp theo");
            System.out.println("Nhập \"có\" để tiếp tục, nhập ký tự bất kỳ để dừng nhập");
            exits = scanner.next();
            if (exits.equals("có")) {
                check = true;
            } else {
                check = false;
            }
        }
    }

    public void addListInvoices(){
        invoices = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoiceService.createInvoice(invoice);
    }

    public void sortListInvoiceWithName(){
        invoiceService.sortListInvoicesWithName();
    }

    public void sortListInvoiceWithQuantity(){
        invoiceService.sortListInvoicesWithQuantity();
    }
    
    public void taoBangKe(){
        InvoiceRepository invoiceRepository = null;
        List<Invoice> invoices = invoiceRepository.getAllInvoices();
        for( int i =0; i< invoices.size();i++){
            System.out.println("invoiceId: " + invoices.get(i).getId() + "Tên: " + invoices.get(i).getTicketBuyer().getName()
                    + " Loại vé: " + invoices.get(i).getTicket().getTypeSeat() + " số lượng: " + invoices.get(i).getQuantity()
                    + " Giá: " + invoices.get(i).getTicket().getPrice());
        }
    }


}
