package ejercicio1;

public class CuentaBancaria 
{
	private String numeroCuenta;
	private double saldo;
	private String titular;
	
	public CuentaBancaria(String numeroCuenta, double saldo, String titular)
	{
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.titular = titular;
	}
	
	public CuentaBancaria()
	{
		this("----------", 0, "Nadie");
	}
	
	public String getNumeroCuenta()
	{
		return this.numeroCuenta;
	}
	
	public double getSaldo()
	{
		return this.saldo;
	}
	
	public String getTitular()
	{
		return this.titular;
	}
	
	public void depositarDinero(double cantidad)
	{
		this.saldo += cantidad;
	}
	
	public void retirarDinero(double cantidad) throws IllegalArgumentException
	{
		if(cantidad > this.saldo)
		{
			throw new IllegalArgumentException("No tiene suficiente saldo.");
		}
		if(this.saldo < 0)
		{
			throw new IllegalArgumentException("Tiene un saldo negativo, no puede retirar dinero.");
		}
		this.saldo -= cantidad;
	}
	
	public void mostrarSaldo()
	{
		System.out.printf("Saldo: %.2f", this.saldo);
	}
}
