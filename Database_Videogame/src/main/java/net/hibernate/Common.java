package net.hibernate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import net.hibernate.entity.Platform;
import net.hibernate.entity.Rating;
import net.hibernate.entity.Videogame;

public class Common 
{
	//MENU - Modular Console Menu with Minimum and Maximum values for input
	public static int menu(String mensaje, Scanner entrada, int min, int max)
	{
		System.out.println(mensaje);
		int respuesta = 0;
		while(respuesta < min || respuesta > max)
		{
			respuesta = inputInt("Input the Desired Option:", entrada);
			if(respuesta < min || respuesta > max)
			{
				System.out.println("\u001B[31mERROR: Invalid Input. Try Again\u001B[37m");
			}
		}
		return respuesta;
	}
	
	//INPUT INT - Input Validation for int variables
	public static int inputInt(String mensaje, Scanner entrada)
	{
		int num = 0;
		boolean inputTrue = false;
		do
		{
			//EXCEPCIÓN - InputMismatchException - Bucle que asegura que el valor introducido es un valor decimal
			try 
			{
				System.out.println(mensaje);
				num = entrada.nextInt();
				entrada.nextLine(); // Depuración Scanner
				inputTrue = true;
			} catch(InputMismatchException excepcion1)
			{
				System.out.println("\u001B[31mERROR: Invalid Input. Try Again\u001B[37m");
				entrada.nextLine(); // Depuración Scanner
			}
		}while(inputTrue == false);
		return num;
	}
	
	//INPUT DOUBLE - Input Validation for double variables
	public static double inputDouble(String mensaje, Scanner entrada)
	{
		double num = 0;
		boolean inputTrue = false;
		do
		{
			//EXCEPCIÓN - InputMismatchException - Bucle que asegura que el valor introducido es un valor decimal
			try 
			{
				System.out.println(mensaje);
				num = entrada.nextDouble();
				entrada.nextLine(); // Depuración Scanner
				inputTrue = true;
			} catch(InputMismatchException excepcion1)
			{
				System.out.println("\u001B[31mERROR: Invalid Input. Try Again\u001B[37m");
				entrada.nextLine(); // Depuración Scanner
			}
		}while(inputTrue == false);
		return num;
	}
	
	//BOOLEAN CHECK - Returns true or false according to user input
	public static boolean booleanCheck(String mensaje, Scanner entradaTeclado)
	{
		boolean resultado = false, flag = false;
		do
		{
			System.out.println(mensaje);
			String respuesta = entradaTeclado.nextLine();
			if(respuesta.toLowerCase().equals("yes")) {
				resultado = true;
				flag = true;
			}
			else if(respuesta.toLowerCase().equals("no"))
			{
				resultado = false;
				flag = true;
			}
			else
			{
				System.out.println("\u001B[31mERROR: Invalid Input. Try Again\u001B[37m");
				flag = false;
			}
		}while(flag == false);
		return resultado;
	}
	
	//RETRIEVE VIDEOGAMES - Returns a LIST of Videogame DATA retrieved from FILE
	public static ArrayList<Videogame> retrieveVideogames(File file){
		ArrayList<Videogame> videogames = new ArrayList<Videogame>();
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()) {
				String[] data = reader.nextLine().split(";");
				try {
					
				}catch(IllegalArgumentException e) {
					System.out.printf("\u001B[31mERROR: %s\u001B[37m\n",e.getMessage());
				}
				Videogame created = new Videogame(
						data[0],
						Platform.valueOf(data[1].toUpperCase()),
						Rating.valueOf(data[2].toUpperCase()),
						Boolean.parseBoolean(data[3].toLowerCase()),
						Boolean.parseBoolean(data[4].toLowerCase()),
						Boolean.parseBoolean(data[5].toLowerCase()),
						Boolean.parseBoolean(data[6].toLowerCase()));
				videogames.add(created);
			}
			for(Videogame videogame : videogames) {
				System.out.println(videogame);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("\u001B[31mERROR: File not Found Exception\u001B[37m");
		}
		return videogames;
	}
}
