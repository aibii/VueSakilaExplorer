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

@RestController  //specialized class that contains @RequestMapping methods
@RequestMapping("/api") //specifies the base URL for the endpoint
@CrossOrigin   // Enable CORS(cross-origin resource sharing) for the endpoint
//building web applications that need to access resources or data from a server that's located on a different domain,
//protocol, or port from the one the application was loaded from.
public class MainController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    ActorRepo actorRepo; //dependency injection -- brain
    FilmRepo filmRepo;  //dependency injection  -- heart

    public MainController(ActorRepo actorRepo, FilmRepo filmRepo) {  //expects to receive instances of ActorRepo and FilmRepo
        this.actorRepo = actorRepo;  //assigns the instances to the actorRepo and filmRepo fields
        this.filmRepo = filmRepo;
    }

    // @GetMapping("/actors")
    // public Page<Actor> getActors() //paginated list of Actor objects
    // {
    //     PageRequest pageRequest = PageRequest.of(1, 5); //set to return 2nd page of results with 5 actor records
    //     Page<Actor> actors = actorRepo.findAll(pageRequest);
    //     return actors;
    // }

    // step 3
    /*
     * The getActors method is now updated to accept a page parameter. The page
     * parameter is used to specify the page number of the results to be returned.
     * The default value of the page parameter is set to 0. The page parameter is
     * used to create a PageRequest object. The PageRequest object is used to
     * retrieve the results from the database. The results are returned as a Page
     * object.
     * 
     * @param page
     * @RequestParam defaultValue = "0"
     */
    // @GetMapping("/actors")
    // public Page<Actor> getActors(@RequestParam(value = "0" ) int page) {
    //     PageRequest pageRequest = PageRequest.of(0, 5);
    //     return actorRepo.findAll(pageRequest);
    // }

    //step 4
    @GetMapping("/actors")
    public Page<Actor> getActors(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        log.info("Fetching actors - page: {}, pageSize: {}", page, pageSize); 
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return actorRepo.findAll(pageRequest);
    }

    //step 2
    // // New endpoint for films
    // @GetMapping("/films")
    // public Page<Film> getFilms() {
    //     PageRequest pageRequest = PageRequest.of(0, 5); 
    //     return filmRepo.findAll(pageRequest);
    // }

    /*
     * The getFilms method is now updated to accept a page parameter. The page
     * parameter is used to specify the page number of the results to be returned.
     * The default value of the page parameter is set to 0. The page parameter is
     * used to create a PageRequest object. The PageRequest object is used to
     * retrieve the results from the database. The results are returned as a Page
     * object.
     * 
     * @RequestParam defaultValue = "0"
     */
    //step 3
    // @GetMapping("/films")
    // public Page<Film> getFilms(@RequestParam(defaultValue = "0") int page) {
    //     // The page size is hardcoded to 5 
    //     PageRequest pageRequest = PageRequest.of(page, 5);
    //     return filmRepo.findAll(pageRequest);
    // }

    @GetMapping("/films")
    public Page<Film> getFilms(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return filmRepo.findAll(pageRequest);
    }
}
