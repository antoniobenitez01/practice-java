package api.rest_videogames;

import org.springframework.data.jpa.repository.JpaRepository;

interface VideogameRepository extends JpaRepository<Videogame, Long> {

}
