package restvideogames.restsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/games")
public class MainController {
	
	@Autowired
	private VideogameRepository videogameRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewVideogame(
			@RequestParam String title,
			@RequestParam String platform,
			@RequestParam Rating rating,
			@RequestParam Boolean isCollection,
			@RequestParam Boolean isRomhack,
			@RequestParam Boolean isFavourite) {
		Videogame videogame = new Videogame();
		videogame.setTitle(title);
		videogame.setPlatform(platform);
		videogame.setRating(rating);
		videogame.setCollection(isCollection);
		videogame.setRomhack(isRomhack);
		videogame.setFavourite(isFavourite);
		videogameRepository.save(videogame);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Videogame> getAllVideogames(){
		return videogameRepository.findAll();
	}
}
