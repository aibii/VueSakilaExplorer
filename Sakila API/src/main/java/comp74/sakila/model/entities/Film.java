package comp74.sakila.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Film {
    @Id
    Integer filmId;
    String title;
    String description;
}