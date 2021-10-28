package repository;

import model.Invoice;
import model.Ticket;
import model.TicketBuyer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {
    public void addToDatabase(Invoice invoice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(invoice);
            session.getTransaction().commit();
            System.out.println("create invoice success");
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Invoice> getAllInvoices() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Invoice> invoices = new ArrayList<>();
        try {
            Query query = session.createQuery("SELECT i FROM Invoice i", Invoice.class);
            invoices = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return invoices;
    }
}
