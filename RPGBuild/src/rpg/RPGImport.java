/*
 * Imported from the PSeInt project
 */
package rpg;

import java.util.Scanner;

public class RPGImport 
{

	public static void main(String[] args) 
	{
		// = VARIABLES =
		Scanner entradaTeclado = new Scanner(System.in);
		String choice;
		int health = 50, strength = 5, specialstrength = 10, mana = 10, maxmana = 10, defense = 3, rpschoice=0;
		int enemyhealth = 50, enemystrength = 7, enemyrps;
		boolean groundspikes = false, enemyonfire = false, playeronfire = false, smitecharged = false, choiceselect = false;
		boolean rpschoiceselect = false, itemchoice = false, ranAway = false, restart = false, restartchoice = false;
	
		do
		{
			System.out.println("=========================="+
							 "\n||   SMALL RPG BATTLE   ||"+
							 "\n||   BECAUSE IM BORED   ||"+
							 "\n|| BUT I FINALLY PORTED ||"+
							 "\n||    IT TO JAVA LOL    ||"+
							 "\n==========================");
			wait(1000);
			while(health > 0 & enemyhealth > 0)
			{
				System.out.println("-----------------------------------------------");
				//Battle Start Dialogue
				System.out.println("=== PLAYER STATS ==="+
								"\n HEALTH = " + health +
								"\n STRENGTH = " + strength +
								"\n DEFENSE = " + defense +
								"\n MANA = " + mana +
								"\n=== ENEMY STATS ==="+
								"\n HEALTH = " + enemyhealth +
								"\n STRENGTH = " + enemystrength);
				System.out.println("-----------------------------------------------");
				wait(3000);
				//MENU LOOP
				do
				{	
					System.out.println("====== ACTION ======"+
							"\nType your next action."+
							"\nYour options are:" +
							"\n - ATTACK" +
							"\n - RUN" +
							"\n - ITEM" +
							"\n - SPECIAL");
					choice = entradaTeclado.nextLine();
					if(choice.equals("ATTACK"))
					{
						enemyhealth = enemyhealth - strength;
						System.out.println("You attacked the enemy, you did " + strength + " points of damage.");
						choiceselect = true;
					}
					else if(choice.equals("RUN"))
					{
						if(rngRun() == false)
						{
							System.out.println("You try to run but fail.");
						}
						else
						{
							System.out.println("You escape succesfully!");
							enemyhealth = 0;
							ranAway = true;
						}
						choiceselect = true;
					}
					else if(choice.equals("ITEM"))
					{
						System.out.println("-----------------------------------------------");
						System.out.println("=== ITEM ==="+
										"\nWhich item would you like to use?"+
										"\nYour current options are:"+
										"\n - MOLOTOV" +
										"\n - SPIKES" +
										"\n - RUM" +
										"\n - POTION");
							
						choice = entradaTeclado.nextLine();
						if(choice.equals("MOLOTOV"))
						{
							System.out.println("You threw a MOLOTOV at the enemy."+
											"\nThe molotov deals 15 damage to the enemy!");
							enemyhealth = enemyhealth - 15;
							enemyonfire = true;
							wait(1500);
							System.out.println("The enemy is now on fire! It will deal damage every round."+
											"\nIf the enemy deals damage to you, you will also be set on fire.");
							choiceselect = true;
						}
						else if(choice.equals("SPIKES"))
						{
							System.out.println("You throw spikes at the enemy!"+
											"\nThe spikes will now deal damage every round.");
							groundspikes = true;
							choiceselect = true;
						}
						else if(choice.equals("RUM"))
						{
							System.out.println("Delicious! You drink a bottle of rum."+
											"\nYou feel stronger but also dizzy."+
											"\nYou have gained 10 Strength points!"+
											"\nYou have lost 5 Health points.");
							strength = strength + 10;
							health = health - 5;
							choiceselect = true;
							
						}
						else if(choice.equals("POTION"))
						{
							System.out.println("-----------------------------------------------");
							System.out.println("= POTION ="+
											"\nWhich potion would you like to use?"+
											"\nYour current options are:"+
											"\n - HEALTH"+
											"\n - MANA"+
											"\n - STRENGTH");
							choice = entradaTeclado.nextLine();
							if(choice.equals("HEALTH"))
							{
								health = health + 5;
								System.out.println("You drink a Health potion!"+
												"\nYou have restored 5 Health points.");
								choiceselect = true;
							}
							else if(choice.equals("MANA"))
							{
								mana = mana + 5;
								System.out.println("You drink a Mana potion!"+
										"\nYou have restored 5 Mana points.");
								choiceselect = true;
							}
							else if(choice.equals("STRENGTH"))
							{
								strength = strength + 5;
								System.out.println("You drink a Strength potion!"+
										"\nYou have restored 5 Strength points.");
								choiceselect = true;
							}
							else
							{
								System.out.println("Invalid command. Try again.");
							}
						}
						else
						{
							System.out.println("Invalid command. Try again.");
						}
					}
					else if(choice.equals("SPECIAL"))
					{
						System.out.println("-----------------------------------------------");
						System.out.println("=== SPECIAL ==="+
										"\nWhich Special move would you like to use?");
						if(smitecharged == false)
						{
							System.out.println("Your current options are: "+
											"\n - TAUNT"+
											"\n - GUARD"+
											"\n - FOCUS"+
											"\n - HEAL"+
											"\n - BLAST");
						}
						else
						{
							System.out.println("Your current options are: "+
									"\n - TAUNT"+
									"\n - GUARD"+
									"\n - SMITE"+
									"\n - HEAL"+
									"\n - BLAST");
						}
						choice = entradaTeclado.nextLine();
						
						if(choice.equals("TAUNT"))
						{
							enemystrength = enemystrength + 5;
							System.out.println("You taunt the enemy!"+
											"\nThe enemy is really mad now!"+
											"\nYou've activated Hard Mode!");
							choiceselect = true;
						}
						else if(choice.equals("GUARD"))
						{
							defense = defense + 3;
							System.out.println("You guard up! Only lasts one turn.");
							choiceselect = true;						
						}
						else if(smitecharged == false && choice.equals("FOCUS"))
						{
							System.out.println("You focus up! You can now use Smite as a Special Move!");
							smitecharged = true;
							choiceselect = true;
						}
						else if(smitecharged == true && choice.equals("SMITE"))
						{
							enemyhealth = enemyhealth - 30;
							System.out.println("Smite thy enemy! You deal 30 Damage points to the enemy."+
											"\nYou can no longer use Smite.");
							smitecharged = false;
							choiceselect = true;
						}
						else if(choice.equals("HEAL"))
						{
							if(mana >= 10)
							{
								health = health + 10;
								mana = mana - 10;
								System.out.println("You use magic to heal yourself!"+
										"\nYou have restored 10 Health points."+
										"\nYou have used 10 Mana points.");
								choiceselect = true;
							}
							else
							{
								System.out.println("You do not have enough mana.");
							}
						}
						else if(choice.equals("BLAST"))
						{
							if(mana >= 10)
							{
								enemyhealth = enemyhealth - (specialstrength + strength);
								mana = mana - 10;
								System.out.println("You concentrate your energy into a powerful blast!"+
												"\nYou have dealt " + (specialstrength + strength) + " Damage points to the enemy.");
								choiceselect = true;
							}
							else
							{
								System.out.println("You do not have enough mana");
							}
						}
						else
						{
							System.out.println("Invalid command. Try again.");
						}
					}
					else
					{
						System.out.println("Invalid command, try again.");
					}
				}while(choiceselect == false);
				wait(3000);
				
				//ROCK PAPER SCISSORS COMBAT
				if(enemyhealth > 0)
				{
					//MENU LOOP
					do
					{
						System.out.println("------------------------"+
										"\nRock, Paper Scissors!"+
										"\nChoose:"+
										"\n - ROCK"+
										"\n - PAPER"+
										"\n - SCISSORS");
						choice = entradaTeclado.nextLine();
						if(choice.equals("ROCK"))
						{
							rpschoice = 0;
							rpschoiceselect = true;
						}
						else if(choice.equals("PAPER"))
						{
							rpschoice = 1;
							rpschoiceselect = true;
						}
						else if(choice.equals("SCISSORS"))
						{
							rpschoice = 2;
							rpschoiceselect = true;
						}
						else
						{
							System.out.println("Invalid command. Try again.");
						}
					}while(rpschoiceselect == false);
					
					enemyrps = (int) (Math.random()*3);
					switch(enemyrps)
					{
					case 0:
						System.out.println("Enemy chose ROCK!");
						break;
					case 1:
						System.out.println("Enemy chose PAPER!");
						break;
					case 2:
						System.out.println("Enemy chose SCISSORS!");
						break;
					}
					wait(1000);
					switch(rps(rpschoice, enemyrps))
					{
					case 1:
						System.out.println("You win! You dodge the attack.");
						break;
					case 2:
						health = health - (enemystrength - defense);
						System.out.println("Draw! The enemy attacks."+
										"\nThe enemy deals " + (enemystrength - defense) + " Damage points.");
						wait(1500);
						if(enemyonfire == true)
						{
							System.out.println("The enemy hurt you while they were on fire."+
											"\nNow you're on fire too.");
							playeronfire = true;
						}
						break;
					case 3:
						health = health - (enemystrength * 2);
						System.out.println("You lose! Critical damage!"+
										"\nThe enemy deals " + (enemystrength * 2) + " Damage points.");
						wait(1500);
						if(enemyonfire == true)
						{
							System.out.println("The enemy hurt you while they were on fire."+
											"\nNow you're on fire too.");
							playeronfire = true;
						}
						break;
					}
				}
				
				wait(1500);
				//STAT CHECKERS
				if(groundspikes == true)
				{
					enemyhealth = enemyhealth - 5;
					System.out.println("The spikes deal damage to the enemy.");
					wait(1500);
				}
				
				if(enemyonfire == true)
				{
					enemyhealth = enemyhealth - 5;
					System.out.println("The fire deals damage to the enemy.");
					wait(1500);
				}
				
				if(playeronfire == true)
				{
					health = health - 5;
					System.out.println("The fire is slowly hurting you.");
					wait(1500);
				}
				
				if(mana < maxmana)
				{
					mana = mana + 1;
					System.out.println("You can feel your mana slowly recharging...");
					wait(1500);
				}
				
				//VARIABLE RETURN TO DEFAULT
				defense = 3;
				choiceselect = false;
				rpschoiceselect = false;
				
				wait(2000);
			}
			
			System.out.println("----------------------------");
			//ENDING
			if(enemyhealth <= 0)
			{
				if(ranAway == true)
				{
					System.out.println("The enemy is nowhere to be seen.");
				}
				else
				{
					System.out.println("The enemy died.");
				}
				System.out.println("You've won! Congratulations.");
			}
			else if(health <= 0)
			{
				System.out.println("You died, too bad."+
								"\nGAME OVER.");
			}
			
			do
			{	
				System.out.println("Would you like to restart?"+
						"\nYES - NO");
				choice = entradaTeclado.nextLine();
				if(choice.equals("YES"))
				{
					restart = true;
					restartchoice = true;
					System.out.println("-----------------------------------------------");
				}
				else if(choice.equals("NO"))
				{
					restart = false;
					restartchoice = true;
				}
				else
				{
					System.out.println("Invalid command. Try again.");
				}
			}while(restartchoice == false);
		}while(restart != false);
		
	}
	
	//METHOD - Wait miliseconds
	public static void wait(int num)
	{
		try 
		{
			Thread.sleep(num);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	//METHOD - Random Run Code
	public static boolean rngRun()
	{
		boolean ranAway = false;
		int runrng;
		runrng = (int) (Math.random()*6);
		switch(runrng)
		{
		case 0,1,3,5:
			ranAway = false;
			break;
		case 2,4:
			ranAway = true;
			break;
		}
		return ranAway;
	}
	
	//METHOD - Rock Paper Scissors
	public static int rps(int rpschoice, int enemyrps)
	{
		int result = 0;
		switch(rpschoice)
		{
		case 0:
			switch(enemyrps)
			{
			case 0:
				result = 2;
				break;
			case 1:
				result = 3;
				break;
			case 2:
				result = 1;
				break;
			}
			break;
		case 1:
			switch(enemyrps)
			{
			case 0:
				result = 1;
				break;
			case 1:
				result = 2;
				break;
			case 2:
				result = 3;
				break;
			}
			break;
		case 2:
			switch(enemyrps)
			{
			case 0:
				result = 3;
				break;
			case 1:
				result = 1;
				break;
			case 2:
				result = 2;
				break;
			}
			break;
		}
		return result;
	}
}
