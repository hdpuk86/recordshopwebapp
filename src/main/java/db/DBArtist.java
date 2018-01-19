package db;

import models.Album;
import models.Artist;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBArtist {
    private static Session session;
    private static Transaction transaction;

    public static void save(Artist artist){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String hql = String.format("delete from %s", "Artist");
            Query query = session.createQuery(hql);
            query.executeUpdate();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Artist> getArtists(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Artist> artists = null;
        try {
            transaction = session.beginTransaction();
            artists = session.createQuery("from Artist").list();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return artists;
    }

    public static void update(Artist artist){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(artist);
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Artist getArtistById(int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Artist artist = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "from Artist where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            artist = (Artist) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return artist;
    }

    public static void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Artist artist = getArtistById(id);
        try {
            transaction = session.beginTransaction();
            session.delete(artist);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
