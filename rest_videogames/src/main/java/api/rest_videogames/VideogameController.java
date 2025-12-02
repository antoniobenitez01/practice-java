package api.rest_videogames;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideogameController {
	
	private final VideogameRepository repository;
	
	VideogameController(VideogameRepository repository){
		this.repository = repository;
	}
	
	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/videogames")
	List<Videogame> all(){
		return repository.findAll();
	}
	// end::get-aggregate-root[]
	
	@PostMapping("/videogames")
	Videogame newVideogame(@RequestBody Videogame newVideogame) {
		return repository.save(newVideogame);
	}
	
	// Single item
	
	@GetMapping("/videogames/{id}")
	Videogame one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new VideogameNotFoundException(id));
	}
	
	@PutMapping("/videogames/{id}")
	Videogame replaceVideogame(@RequestBody Videogame newVideogame, @PathVariable Long id) {
		return repository.findById(id)
				.map(videogame -> {
					videogame.setTitle(newVideogame.getTitle());
					videogame.setPlatform(newVideogame.getPlatform());
					videogame.setRating(newVideogame.getRating());
					videogame.setCollection(newVideogame.isCollection());
					videogame.setRomhack(newVideogame.isRomhack());
					videogame.setFavourite(newVideogame.isFavourite());
					return repository.save(videogame);
				})
				.orElseGet(() -> {
					return repository.save(newVideogame);
				});
	}
	
	@DeleteMapping("/videogames/{id}")
	void deleteVideogame(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
