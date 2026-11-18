/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Movie;

import java.time.LocalDateTime;
import java.util.List;

public class DbSmokeTest {
    public static void main(String[] args) throws Exception {
        Repository repo = new SqlRepository();

        Movie m = new Movie(
                0,
                "DB TEST",
                "https://example.com",
                "If you see this in DB, Java->SQL works.",
                "assets\\test.jpg",
                LocalDateTime.now()
        );

        int id = repo.createMovie(m);
        System.out.println("Inserted ID = " + id);

        List<Movie> all = repo.selectMovies();
        System.out.println("Movies in DB = " + all.size());

        // optional cleanup
        repo.deleteMovie(id);
        System.out.println("Deleted ID = " + id);
    }
}
