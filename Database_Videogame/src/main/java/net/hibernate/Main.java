package net.hibernate;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.hibernate.dao.VideogameDAO;
import net.hibernate.entity.Videogame;
import net.hibernate.util.HibernateUtil;

public class Main 
{
	public static void main(String[] args) 
	{
		System.out.println("\u001B[32m\n=== VIDEOGAME DATABASE CONSOLE ===\n");
		
		System.out.println("\u001B[36m--- DEBUG: RETRIEVING OBJECTS FROM CSV FILE ...\n\u001B[37m");
		List<Videogame> objectsVideogame = Common.retrieveVideogames(new File("games.csv"));
		objectsVideogame.forEach(videogame -> System.out.println(videogame));
		
		System.out.println("\u001B[36m\n--- DEBUG: INSERTING VIDEOGAMES IN DATABASE ...\n\u001B[33m");
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			for(Videogame videogame : objectsVideogame) {
				session.persist(videogame);
			}
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n\u001B[36m--- DEBUG: INITIALIZING FRONT END ...\n\u001B[37m");
		Frontend front = new Frontend();
		front.show();
	}
}
