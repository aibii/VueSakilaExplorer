package comp74.sakila.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comp74.sakila.model.entities.Actor;
import comp74.sakila.model.entities.Film;
import comp74.sakila.model.repos.ActorRepo;
import comp74.sakila.model.repos.FilmRepo;

@RestController 
@RequestMapping("/api") 
@CrossOrigin   
public class MainController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    ActorRepo actorRepo;
    FilmRepo filmRepo;  

    public MainController(ActorRepo actorRepo, FilmRepo filmRepo) {
        this.actorRepo = actorRepo;  
        this.filmRepo = filmRepo;
    }

    @GetMapping("/actors")
    public Page<Actor> getActors(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        log.info("Fetching actors - page: {}, pageSize: {}", page, pageSize); 
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return actorRepo.findAll(pageRequest);
    }

    @GetMapping("/films")
    public Page<Film> getFilms(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return filmRepo.findAll(pageRequest);
    }
}
