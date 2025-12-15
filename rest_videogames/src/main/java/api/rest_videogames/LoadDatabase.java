package api.rest_videogames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	  String url = String.format("jdbc:mysql://localhost:3306/played");
	  Connection conn = Common.connectionSQL(url,"hibernate","123");
	  ArrayList<Videogame> list = retrieveVideogames(conn);
	  return args -> {
		  for(Videogame videogame : list) {
			  log.info("Preloading " + repository.save(videogame));
		  }
	  };
  }
  
  public static ArrayList<Videogame> retrieveVideogames(Connection conn){
		ArrayList<Videogame> videogames = new ArrayList<Videogame>();
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM videogame");
				ResultSet rs = stmt.executeQuery()){
			while(rs.next()) {
				Videogame videogame = new Videogame(
						rs.getString("title"),
						rs.getString("platform"),
						Rating.values()[rs.getInt("rating")],
						rs.getInt("isCollection") == 1 ? true : false,
						rs.getInt("isRomhack") == 1 ? true : false,
						rs.getInt("isFavourite") == 1 ? true : false);
				if(videogame != null) {
					videogames.add(videogame);
				}
			}
		}catch(SQLException e) {
			System.out.println("\u001B[31mERROR: " + e.getMessage() + "\u001B[37m");
		}
		return videogames;
	}
}