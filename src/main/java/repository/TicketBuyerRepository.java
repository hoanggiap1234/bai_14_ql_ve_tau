package repository;

import model.Ticket;
import model.TicketBuyer;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.Parameter;
import java.util.ArrayList;
import java.util.List;

public class TicketBuyerRepository {

    public void addToDatabase(TicketBuyer ticketBuyer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(ticketBuyer);
            session.getTransaction().commit();
            System.out.println("create buyer success");
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public List<TicketBuyer> findAllBuyer() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<TicketBuyer> ticketBuyerList = new ArrayList<>();
        try {
            Query query = session.createQuery("SELECT t FROM TicketBuyer t", TicketBuyer.class);
            ticketBuyerList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ticketBuyerList;
    }

    public Object getMaxId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object maxIDselected = null;
        Integer maxID = 0;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT max(id) as maxID FROM ticket_buyer");
            maxIDselected = query.getSingleResult();
            if (maxIDselected == null) {
                maxIDselected = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return maxIDselected;
    }

    public TicketBuyer getBuyerById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<TicketBuyer> query = session.createQuery("SELECT t FROM TicketBuyer t WHERE t.id = :p_ticket_id ");
            query.setParameter("p_ticket_id", id);
            return !query.list().isEmpty() ? query.getSingleResult() : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public static void main(String[] args) {
        new TicketBuyerRepository().getBuyerById(10000);
    }
}
