package net.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.hibernate.entity.Videogame;
import net.hibernate.util.HibernateUtil;

public class VideogameDAO 
{
	//SELECT VIDEOGAME - Returns a LIST of DATA retrieved from DATABASE
	public static List<Videogame> selectVideogame() {
		List<Videogame> videogames = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			videogames = session.createQuery("from Videogame",Videogame.class).list();
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}
		return videogames;
	}
	
	//UPDATE VIDEOGAME - INSERT or UPDATE object introduced via parameter in DATABASE
	public static void updateVideogame(Videogame videogame) 
	{
		if(videogame != null) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()){
				transaction = session.beginTransaction();
				session.merge(videogame);
				transaction.commit();
			}catch(Exception e ) {
				if(transaction != null) {
					transaction.rollback();
				}
				System.out.println(e.getMessage());
			}
		}else {
			System.out.println("\u001B[31mERROR: NULL POINTER at parameter 'videogame'\u001B[37m");
		}
	}
	
	//DELETE VIDEOGAME - DELETE object introduced via parameter in DATABASE
	public static void deleteContacto(Videogame videogame) 
	{
		if(videogame != null) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()){
				transaction = session.beginTransaction();
				session.remove(videogame);
				transaction.commit();
			}catch(Exception e ) {
				if(transaction != null) {
					transaction.rollback();
				}
				System.out.println(e.getMessage());
			}
		}else {
			System.out.println("\u001B[31mERROR: NULL POINTER at parameter 'videogame'\u001B[37m");
		}
	}
}
