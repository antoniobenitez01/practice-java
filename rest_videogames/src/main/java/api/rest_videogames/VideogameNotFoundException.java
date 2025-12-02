package api.rest_videogames;

public class VideogameNotFoundException extends RuntimeException {
	VideogameNotFoundException(Long id){
		super("Could not find Videogame " + id);
	}
}
