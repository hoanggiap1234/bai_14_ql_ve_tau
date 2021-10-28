package service;

import convert.Convert;
import enums.TypePerSonBuy;
import enums.TypeSeat;
import model.Ticket;
import model.TicketBuyer;
import repository.TicketBuyerRepository;
import repository.TicketRepository;

import java.util.List;
import java.util.Scanner;

public class TicketService {
    public static int autoId = 10000;
    private Ticket ticket;
    private TicketRepository ticketRepository = new TicketRepository();


    public void setId() {
        int maxID = Integer.parseInt(ticketRepository.getMaxId().toString());
        if (maxID == 0) {
            autoId = 10000;
        } else {
            autoId = maxID;
        }
        autoId++;
    }

    public void addTicket() {

        ticket = new Ticket();
        Scanner scanner = new Scanner(System.in);
        setId();
        ticket.setId(autoId);
        System.out.println("Loại ghế: ");
        System.out.println("1: Ghế cứng");
        System.out.println("2: Ghế mềm");
        System.out.println("3: Giường nằm");
        String typeSeat = scanner.next();

        while (!Convert.isNumeric(String.valueOf(typeSeat)) || Integer.parseInt(String.valueOf(typeSeat)) > 3 || Integer.parseInt(String.valueOf(typeSeat)) < 1) {
            System.out.println("Chọn Loại ghế là số nguyên từ 1 đến 3: ");
            System.out.println("1: Ghế cứng");
            System.out.println("2: Ghế mềm");
            System.out.println("3: Giường nằm");
            typeSeat = scanner.next();
        }
        int typeBuyer = Integer.parseInt(String.valueOf(typeSeat));
        switch (typeBuyer) {
            case 1:
                ticket.setTypeSeat(TypeSeat.HARD.getDescription());
                break;
            case 2:
                ticket.setTypeSeat(TypeSeat.SOFT.getDescription());
                break;
            case 3:
                ticket.setTypeSeat(TypeSeat.BED.getDescription());
                break;
        }
        if(ticketRepository.getTicketByTypeSeat(ticket.getTypeSeat()) != null){
            System.out.println("Loại vé " + ticket.getTypeSeat() +  " đã tồn tại");
            return;
        }
        System.out.println("Đơn giá: ");
        String price = scanner.next();
        while (!Convert.isNumeric(price)) {
            System.out.println("Nhập lại, Giá phải là dạng số: ");
            price = scanner.next();
        }
        ticket.setPrice(Integer.parseInt(price));

        ticketRepository.createTicket(ticket);
    }

    public void printAllTicket() {
        List<Ticket> tickets;
        tickets = ticketRepository.findAllTicket();
        if (tickets == null) {
            System.out.println(" Không có dữ liệu vé");
        } else {
            tickets.forEach(ticket -> {
                System.out.println("Mã vé: " + ticket.getId() + ";Loại ghế: " + ticket.getTypeSeat() + "; Giá: " + ticket.getPrice());
            });
        }
    }

    public Ticket getTicketById(int id) {
        Ticket ticket = new Ticket();
        try {
            ticket = ticketRepository.getTicketById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
