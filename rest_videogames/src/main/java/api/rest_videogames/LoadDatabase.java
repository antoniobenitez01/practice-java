package api.rest_videogames;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(VideogameRepository repository) {
	  ArrayList<Videogame> list = retrieveVideogames(new File("games.csv"));
	  return args -> {
		  for(Videogame videogame : list) {
			  log.info("Preloading " + repository.save(videogame));
		  }
	  };
  }
  
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
						data[1],
						Rating.valueOf(data[2].toUpperCase()),
						Boolean.parseBoolean(data[3].toLowerCase()),
						Boolean.parseBoolean(data[4].toLowerCase()),
						Boolean.parseBoolean(data[5].toLowerCase()));
				videogames.add(created);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("\u001B[31mERROR: File not Found Exception\u001B[37m");
		}
		return videogames;
	}
}