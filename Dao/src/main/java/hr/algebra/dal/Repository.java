/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Movie;
import java.util.List;
import java.util.Optional;
import hr.algebra.model.User;
import hr.algebra.model.Actor;



/**
 *
 * @author daniel.bele
 */
public interface Repository {

    int createMovie(Movie movie) throws Exception;

    void createMovies(List<Movie> movies) throws Exception;

    void updateMovie(int id, Movie data) throws Exception;

    void deleteMovie(int id) throws Exception;
    
    void deleteAllMovies() throws Exception;
    int createUser(User user) throws Exception;
    int createActor(Actor actor) throws Exception;
    void updateActor(int id, Actor data) throws Exception;
    void deleteActor(int id) throws Exception;

    List<Actor> selectActors() throws Exception;
    List<Actor> selectActorsForMovie(int movieId) throws Exception;

    void addActorToMovie(int movieId, int actorId) throws Exception;
    void removeActorFromMovie(int movieId, int actorId) throws Exception;




    Optional<Movie> selectMovie(int id) throws Exception;
    Optional<User> selectUserByUsername(String username) throws Exception;

    List<Movie> selectMovies() throws Exception;

}
