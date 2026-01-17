package net.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.hibernate.entity.Videogame;
import net.hibernate.util.HibernateUtil;

public class Main 
{
	public static void main(String[] args) 
	{
		System.out.println("\u001B[32m\n=== VIDEOGAME DATABASE CONSOLE ===\n");
		
		System.out.println("\u001B[36m\n--- DEBUG: INSERTING VIDEOGAMES IN DATABASE ...\n\u001B[33m");
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.persist(new Videogame());
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			System.out.println(e.getMessage());
		}
	}
}
