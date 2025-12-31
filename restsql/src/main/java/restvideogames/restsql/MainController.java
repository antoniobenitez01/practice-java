package restvideogames.restsql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/games")
public class MainController {
	
	@Autowired
	private VideogameRepository videogameRepository;
	
	@PostMapping(path="/add")
	public ResponseEntity<String> addNewVideogame(@RequestBody Videogame data) {
		Videogame videogame = new Videogame();
		videogame.setTitle(data.getTitle());
		videogame.setPlatform(data.getPlatform());
		videogame.setRating(data.getRating());
		videogame.setCollection(data.isCollection());
		videogame.setFangame(data.isFangame());
		videogame.setFlash(data.isFlash());
		videogame.setFavourite(data.isFavourite());
		videogameRepository.save(videogame);
		return ResponseEntity.ok("Added Successfully");
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Videogame> getAllVideogames(){
		return videogameRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody Optional<Videogame> getVideogame(@PathVariable("id") int id){
		return videogameRepository.findById(id);
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<String>
    updateDepartment(@RequestBody Videogame videogame,
                     @PathVariable("id") int id)
    {
		Videogame toUpdate = videogameRepository.findById(id).get();
		if(videogame.getTitle() != "" && videogame.getTitle() != null) {
			toUpdate.setTitle(videogame.getTitle());
		}
		if(videogame.getPlatform() != null) {
			toUpdate.setPlatform(videogame.getPlatform());
		}
		if(videogame.getRating() != null) {
			toUpdate.setRating(videogame.getRating());
		}
		if(videogame.isCollection() != null) {
			toUpdate.setCollection(videogame.isCollection());
		}
		if(videogame.isFangame() != null) {
			toUpdate.setFangame(videogame.isFangame());
		}
		if(videogame.isFlash() != null) {
			toUpdate.setFlash(videogame.isFlash());
		}
		if(videogame.isFavourite() != null) {
			toUpdate.setFavourite(videogame.isFavourite());
		}
		videogameRepository.save(toUpdate);
		return ResponseEntity.ok("Updated successfully");
    }
	
	// Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") int id)
    {
        videogameRepository.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
