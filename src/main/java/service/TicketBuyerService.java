package service;

import convert.Convert;
import enums.TypePerSonBuy;
import model.TicketBuyer;
import repository.TicketBuyerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBuyerService {
    public static int autoId = 10000;
    private TicketBuyer ticketBuyer;

    private TicketBuyerRepository ticketBuyerRepository = new TicketBuyerRepository();

    public void setId() {
        int maxID = Integer.parseInt(ticketBuyerRepository.getMaxId().toString());
        if (maxID == 0) {
            autoId = 10000;
        } else {
            autoId = maxID;
        }
        autoId ++;
    }

    public void addBuyer() {
        ticketBuyer = new TicketBuyer();
        Scanner scanner = new Scanner(System.in);
        setId();
        ticketBuyer.setId(autoId);
        System.out.println("Họ tên: ");
        String name = scanner.nextLine();
        ticketBuyer.setName(name);

        System.out.println("Địa chỉ: ");
        String address = scanner.nextLine();
        ticketBuyer.setAddress(address);

        System.out.println("Số điện thoại: ");
        String phoneNumber;
        do {
            System.out.println("Nhập lại, Số điện thoại là dạng số: ");
            phoneNumber = scanner.next();
        } while (!Convert.isNumeric(phoneNumber));
        ticketBuyer.setPhoneNumber(phoneNumber);

        System.out.println("Loại người mua vé: ");
        System.out.println("1: Mua lẻ");
        System.out.println("2: Mua tập thể");
        System.out.println("3: Mua online");
        String inputTypeByer = scanner.next();

        while (!Convert.isNumeric(String.valueOf(inputTypeByer)) || Integer.parseInt(String.valueOf(inputTypeByer)) > 3 || Integer.parseInt(String.valueOf(inputTypeByer)) < 1) {
            System.out.println("Chọn Loại người mua vé là số nguyên từ 1 đến 3: ");
            System.out.println("1: Mua lẻ");
            System.out.println("2: Mua tập thể");
            System.out.println("3: Mua online");
            inputTypeByer = scanner.next();
        }
        int typeBuyer = Integer.parseInt(String.valueOf(inputTypeByer));
        switch (typeBuyer) {
            case 1:
                ticketBuyer.setTypePersonBuy(TypePerSonBuy.RETAILS.getType());
                break;
            case 2:
                ticketBuyer.setTypePersonBuy(TypePerSonBuy.COLLECTIVE_BUY.getType());
                break;
            case 3:
                ticketBuyer.setTypePersonBuy(TypePerSonBuy.ONLINE.getType());
                break;
        }
        ticketBuyerRepository = new TicketBuyerRepository();
        ticketBuyerRepository.addToDatabase(ticketBuyer);
    }

    public void printAllBuyer() {
        List<TicketBuyer> ticketBuyerList;
        ticketBuyerList = ticketBuyerRepository.findAllBuyer();
        for (int i = 0; i < ticketBuyerList.size(); i++) {
            TicketBuyer ticketBuyer = ticketBuyerList.get(i);
            System.out.println("Mã khách: " + ticketBuyer.getId() + "; Tên: " + ticketBuyer.getName() + "; Địa chỉ: " + ticketBuyer.getAddress()
                    + "; SĐT: " + ticketBuyer.getPhoneNumber() + "; Loại khách: " + ticketBuyer.getTypePersonBuy());
        }

    }
}
