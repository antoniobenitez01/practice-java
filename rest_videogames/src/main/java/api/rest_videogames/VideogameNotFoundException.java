package api.rest_videogames;

public class VideogameNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	VideogameNotFoundException(Long id){
		super("Could not find Videogame " + id);
	}
}
