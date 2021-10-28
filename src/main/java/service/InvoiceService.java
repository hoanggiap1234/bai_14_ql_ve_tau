package service;

import convert.Convert;
import model.Invoice;
import model.Ticket;
import model.TicketBuyer;
import repository.InvoiceRepository;
import repository.TicketBuyerRepository;
import repository.TicketRepository;

import java.util.*;

public class InvoiceService {
//    private Invoice invoice;

    private InvoiceRepository invoiceRepository = new InvoiceRepository();
    private TicketRepository ticketRepository = new TicketRepository();
    private TicketBuyerRepository ticketBuyerRepository = new TicketBuyerRepository();

    public void createInvoice(Invoice invoice) {
        Scanner scanner = new Scanner(System.in);
        TicketBuyer ticketBuyer = new TicketBuyer();
        // Nhập mã hóa đơn, tạm thời chưa check trùng mã hóa đơn
//        System.out.println("Nhập mã hóa đơn");
//        System.out.println("Ví dụ: IV00001, IV00002 ");
//        String code = scanner.next();

        //1. Nhập mã người mua
        System.out.println("Nhập mã người mua:");
        String byerId = scanner.next();

        while (ticketBuyerRepository.getBuyerById(Integer.parseInt(byerId)) == null) {
            System.out.println("Nhập mã người mua: ");
            byerId = scanner.next();
        }
        ticketBuyer = ticketBuyerRepository.getBuyerById(Integer.parseInt(byerId));

        //2. Nhập mã vé
        System.out.println("Hóa đơn mua vé của: " + ticketBuyer.getName());
        List<Ticket> tickets = new ArrayList<>();
        String qtyTicket = null;
        
        int count = 1;
        String exits;
        Boolean check = false;

        do {
            Ticket ticket;

            System.out.println("Bạn được mua tối đa 4 loại vé: ");
            System.out.println("Loại vé thứ: " + count);
            System.out.println("Nhập mã loại vé cần mua: ");
            String ticketId = scanner.next();

            while (ticketRepository.getTicketById(Integer.parseInt(ticketId)) == null) {
                System.out.println("Nhập mã loại vé cần mua: ");
                ticketId = scanner.next();
            }
            ticket = ticketRepository.getTicketById(Integer.parseInt(ticketId));
            System.out.println("ticket"  + ticket.toString());
            //2.1:  Kiểm tra trùng ticket
            if (tickets.isEmpty()) {
                tickets.add(ticket);

                //3. Nhập số lượng vé theo mã vé
                do {
                    System.out.println("Nhập Số lượng vé cần mua:");
                    System.out.println("Bạn được mua tối đa 20 vé:");
                    qtyTicket = scanner.next();
                } while (!Convert.isNumeric(qtyTicket) || Integer.parseInt(qtyTicket) < 1 || Integer.parseInt(qtyTicket) > 20);
              

            } else {
//                System.out.println("list ticket: " + tickets);
                for (int i = 0; i < tickets.size(); i++) {
                    if (tickets.get(i).getId() == ticket.getId()) {
                        System.out.println("bạn đã chọn loại vé:  " + ticket.getTypeSeat());
                        System.out.println("Vui lòng chọn loại khác");
                        ticketId = scanner.next();
                    }
                }
            }
            invoice = new Invoice();
//            invoice.setCode(code);
            invoice.setTicketBuyer(ticketBuyer);
            invoice.setTicket(ticket);
            invoice.setQuantity(Integer.parseInt(qtyTicket));

            invoiceRepository.addToDatabase(invoice);
            //4. xử lý thoát nhập mua vé ( số lượng nhỏ hơn
            count++;
            System.out.println("Bạn có muốn nhập tiếp không");
            System.out.println("Nhập \"thoat\" để dừng nhập loại vé ");
            exits = scanner.next();
            if( exits.equals("thoat")){
                check = true;
                return;
            }else {
                check = false;
            }
//            System.out.println("count: " + count);
//            System.out.println("exits: " + exits);
//            System.out.println("check: " + check);
        } while (count < 5);

        System.out.println("Hóa đơn bạn vừa nhập: ");
        invoice.toString();
    }

    public void printAllInvoice(){
        List<Invoice> invoices = invoiceRepository.getAllInvoices();
        for( int i =0; i< invoices.size();i++){
            System.out.println("invoiceId: " + invoices.get(i).getId() + "Tên: " + invoices.get(i).getTicketBuyer().getName()
            + " Loại vé: " + invoices.get(i).getTicket().getTypeSeat() + " số lượng: " + invoices.get(i).getQuantity());
        }
    }

    public  void sortListInvoicesWithName(){
        List<Invoice>  invoices;
        invoices = invoiceRepository.getAllInvoices();
        Collections.sort(invoices, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice o1, Invoice o2) {
                return o1.getTicketBuyer().getName().compareTo(o2.getTicketBuyer().getName());
            }
        });

        System.out.println("Danh sách hóa đơn vừa sắp xếp theo tên: ");
        for( int i =0; i< invoices.size();i++){
            System.out.println("invoiceId: " + invoices.get(i).getId() + "Tên: " + invoices.get(i).getTicketBuyer().getName()
                    + " Loại vé: " + invoices.get(i).getTicket().getTypeSeat() + " số lượng: " + invoices.get(i).getQuantity());
        }
    }

    public  void sortListInvoicesWithQuantity(){
        List<Invoice>  invoices;
        invoices = invoiceRepository.getAllInvoices();
        Collections.sort(invoices, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice o1, Invoice o2) {
                return o2.getQuantity()-o1.getQuantity();
            }
        });

        System.out.println("Danh sách hóa đơn vừa sắp xếp theo số lượng: ");
        for( int i =0; i< invoices.size();i++){
            System.out.println("invoiceId: " + invoices.get(i).getId() + "Tên: " + invoices.get(i).getTicketBuyer().getName()
                    + " Loại vé: " + invoices.get(i).getTicket().getTypeSeat() + " số lượng: " + invoices.get(i).getQuantity());
        }
    }

}
