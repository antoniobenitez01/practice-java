package ejercicio1;

public class Persona 
{
	private String dni;
	private String nombre;
	private int edad;
	
	public Persona(String dni, String nombre, int edad)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Persona()
	{
		this("XXXXXXXXA", "Vacío", 0);
	}
	
	public String getDNI()
	{
		return this.dni;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public int getEdad()
	{
		return this.edad;
	}
	
	public void setDNI(String dni)
	{
		this.dni = dni;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public void setEdad(int edad)
	{
		this.edad = edad;
	}
	
	public String toString()
	{
		return String.format("DNI: %s \nNombre: %s \nEdad: %d", this.dni, this.nombre, this.edad);
	}
}
