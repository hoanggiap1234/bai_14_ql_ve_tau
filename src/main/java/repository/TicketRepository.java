package repository;

import model.Ticket;
import model.TicketBuyer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    public void createTicket(Ticket ticket) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(ticket);
            session.getTransaction().commit();
            System.out.println("create ticket success");
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Object getMaxId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object maxIDselected = null;
        Integer maxID = 0;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT max(id) as maxID FROM ticket");
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

    public List<Ticket> findAllTicket() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ticket> tickets = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT t FROM Ticket t", Ticket.class);
            tickets = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return tickets;
    }

    public Ticket getTicketById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Ticket> query = session.createQuery("SELECT t FROM Ticket t WHERE t.id = :p_ticket_id ");
            query.setParameter("p_ticket_id", id);
            return !query.list().isEmpty() ? query.getSingleResult() : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public Ticket getTicketByTypeSeat(String typeSeat) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Ticket> query = session.createQuery("SELECT t FROM Ticket t WHERE t.id = :p_type_seat ");
            query.setParameter("p_type_seat", typeSeat);
            return !query.list().isEmpty() ? query.getSingleResult() : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}