package pl.ing.streaming.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ing.streaming.app.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Metody do wyszukiwania filmów
}
