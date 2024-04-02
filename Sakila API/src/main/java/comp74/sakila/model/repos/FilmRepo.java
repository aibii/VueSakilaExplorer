package comp74.sakila.model.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import comp74.sakila.model.entities.Film;

public interface FilmRepo extends PagingAndSortingRepository <Film,Long>{
    
}
