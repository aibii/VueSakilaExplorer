package comp74.sakila.model.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import comp74.sakila.model.entities.Actor;

public interface ActorRepo extends PagingAndSortingRepository<Actor, Long>{
    
}
