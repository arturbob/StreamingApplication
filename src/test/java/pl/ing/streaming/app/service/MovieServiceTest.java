package pl.ing.streaming.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.ing.streaming.app.model.Movie;
import pl.ing.streaming.app.repository.MovieRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllMovies_ShouldReturnAllMovies() {
        Movie movie1 = new Movie("", 2023);
        Movie movie2 = new Movie(/* ustaw odpowiednie pola */);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        List<Movie> movies = movieService.findAllMovies();

        assertNotNull(movies);
        assertEquals(2, movies.size());
        verify(movieRepository).findAll();
    }

    @Test
    public void findMovieById_ShouldReturnMovie() {
        Long movieId = 1L;
        Optional<Movie> movie = Optional.of(new Movie(/* ustaw odpowiednie pola */));
        when(movieRepository.findById(movieId)).thenReturn(movie);

        Optional<Movie> found = movieService.findMovieById(movieId);

        assertTrue(found.isPresent());
        assertEquals(movie.get(), found.get());
        verify(movieRepository).findById(movieId);
    }

    @Test
    public void saveOrUpdateMovie_ShouldReturnSavedMovie() {
        Movie movie = new Movie(/* ustaw odpowiednie pola */);
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie savedMovie = movieService.saveOrUpdateMovie(movie);

        assertNotNull(savedMovie);
        verify(movieRepository).save(movie);
    }

    @Test
    public void deleteMovie_ShouldDeleteMovie() {
        Long movieId = 1L;

        movieService.deleteMovie(movieId);

        verify(movieRepository).deleteById(movieId);
    }
}
