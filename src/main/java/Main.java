import service.InvoiceService;
import service.TicketBuyerService;
import service.TicketService;

import java.util.Scanner;

public class Main {


    private static void menu() {
        TicketManagerment ticketManagerment = new TicketManagerment();
        TicketBuyerService ticketBuyerService = new TicketBuyerService();
        TicketService ticketService = new TicketService();
        InvoiceService invoiceService = new InvoiceService();
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    ticketManagerment.addListBuyer();
                    ticketBuyerService.printAllBuyer();
                    break;
                case 2:
                    ticketService.addTicket();
                    ticketService.printAllTicket();
                    break;
                case 3:
                   ticketManagerment.addListInvoices();
                    break;
                case 4:
                    System.out.println("sắp xếp theo tên: ");
                    ticketManagerment.sortListInvoiceWithName();
                    break;
                case 5:
                    System.out.println("Sắp xếp theo số lượng mua");
                    ticketManagerment.sortListInvoiceWithQuantity();
                    break;
                case 6:
                    System.out.println("Lập bảng kê ");
                    ticketManagerment.taoBangKe();
                    break;
                case 7:
                    System.exit(0);
            }

        } while (true);
    }

    private static int functionChoice() {
        System.out.println("\n --------QUẢN LÝ BÁN VÉ TÀU HỎA-------");

        System.out.println("1. Nhập danh sách người mua vé. In ra danh sách người mua đã có  ");
        System.out.println("2. Nhập danh sách loại vé.  In ra danh sách các loại vé đã có  ");
        System.out.println("3. Nhập danh sách hóa đơn mua vé cho mỗi người mua");
        System.out.println("4. Sắp xếp danh sách HÓA ĐƠN theo họ tên người mua");
        System.out.println("5. Sắp xếp danh sách HÓA ĐƠN theo số lượng vé mua");
        System.out.println("6. Lập bảng kê số tiền phải trả cho mỗi người mua    ");
        System.out.println("7. Thoát");
        int functionChoice;
        do {
            functionChoice = new Scanner(System.in).nextInt();
            if (functionChoice >= 1 && functionChoice <= 6) {
                break;
            }
            System.out.print("Chức năng chọn không hợp lệ, vui lòng chọn lại: ");
        } while (true);
        return functionChoice;
    }

    public static void main(String[] args) {
        menu();
////        TicketBuyerService ticketBuyerService = new TicketBuyerService();
////        ticketBuyerService.printAllBuyer();
//        InvoiceService invoiceService = new InvoiceService();
////        invoiceService.sortListInvoices();
//        invoiceService.printAllInvoice();

    }
}
