package comp74.sakila.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Actor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long actorId;       // actor_id
    String firstName;   // first_name
    String lastName;    // last_name
}
