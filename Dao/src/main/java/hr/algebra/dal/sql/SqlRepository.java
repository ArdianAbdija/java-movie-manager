/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import hr.algebra.model.User;
import hr.algebra.model.Actor;



public class SqlRepository implements Repository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";

    private static final String CREATE_MOVIE = "{ CALL dbo.createMovie (?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL dbo.updateMovie (?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL dbo.deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL dbo.selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL dbo.selectMovies }";
    private static final String DELETE_ALL_MOVIES = "{ CALL dbo.deleteAllMovies }";
    
    private static final String ID_USER = "id";
private static final String USERNAME = "username";
private static final String PASSWORD_HASH = "password_hash";
private static final String ROLE = "role";

private static final String SELECT_USER_BY_USERNAME = "{ CALL dbo.selectUserByUsername (?) }";
private static final String CREATE_USER = "{ call dbo.createUser(?, ?, ?, ?) }";
private static final String ID = "id";

private static final String ID_ACTOR = "id_actor";
private static final String NAME = "name";

private static final String CREATE_ACTOR = "{ call dbo.createActor(?, ?) }";
private static final String UPDATE_ACTOR = "{ call dbo.updateActor(?, ?) }";
private static final String DELETE_ACTOR = "{ call dbo.deleteActor(?) }";
private static final String SELECT_ACTORS = "{ call dbo.selectActors }";
private static final String SELECT_ACTORS_FOR_MOVIE = "{ call dbo.selectActorsForMovie(?) }";
private static final String ADD_ACTOR_TO_MOVIE = "{ call dbo.addActorToMovie(?, ?) }";
private static final String REMOVE_ACTOR_FROM_MOVIE = "{ call dbo.removeActorFromMovie(?, ?) }";


@Override
public int createActor(Actor actor) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {

        stmt.setString(1, actor.getName());
        stmt.registerOutParameter(2, Types.INTEGER);

        stmt.executeUpdate();
        return stmt.getInt(2);
    }
}

@Override
public void updateActor(int id, Actor data) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {

        stmt.setInt(1, id);
        stmt.setString(2, data.getName());
        stmt.executeUpdate();
    }
}

@Override
public void deleteActor(int id) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {

        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}

@Override
public List<Actor> selectActors() throws Exception {
    List<Actor> actors = new ArrayList<>();
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(SELECT_ACTORS);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            actors.add(new Actor(
                    rs.getInt(ID_ACTOR),
                    rs.getString(NAME)
            ));
        }
    }
    return actors;
}

@Override
public List<Actor> selectActorsForMovie(int movieId) throws Exception {
    List<Actor> actors = new ArrayList<>();
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(SELECT_ACTORS_FOR_MOVIE)) {

        stmt.setInt(1, movieId);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt(ID_ACTOR),
                        rs.getString(NAME)
                ));
            }
        }
    }
    return actors;
}

@Override
public void addActorToMovie(int movieId, int actorId) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(ADD_ACTOR_TO_MOVIE)) {

        stmt.setInt(1, movieId);
        stmt.setInt(2, actorId);
        stmt.executeUpdate();
    }
}

@Override
public void removeActorFromMovie(int movieId, int actorId) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(REMOVE_ACTOR_FROM_MOVIE)) {

        stmt.setInt(1, movieId);
        stmt.setInt(2, actorId);
        stmt.executeUpdate();
    }
}



    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(LINK, movie.getLink());
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(PICTURE_PATH, movie.getPicturePath());
            stmt.setString(PUBLISHED_DATE, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE);
        }
    }
    @Override
public void deleteAllMovies() throws Exception {
    try (Connection con = DataSourceSingleton.getInstance().getConnection();
         CallableStatement stmt = con.prepareCall(DELETE_ALL_MOVIES)) {
        stmt.executeUpdate();
    }
}



    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Movie movie : movies) {
                stmt.setString(TITLE, movie.getTitle());
                stmt.setString(LINK, movie.getLink());
                stmt.setString(DESCRIPTION, movie.getDescription());
                stmt.setString(PICTURE_PATH, movie.getPicturePath());
                stmt.setString(PUBLISHED_DATE, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
                stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateMovie(int id, Movie data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {

            stmt.setString(TITLE, data.getTitle());
            stmt.setString(LINK, data.getLink());
            stmt.setString(DESCRIPTION, data.getDescription());
            stmt.setString(PICTURE_PATH, data.getPicturePath());
            stmt.setString(PUBLISHED_DATE, data.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {

            stmt.setInt(ID_MOVIE, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getString(LINK),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER)));
            }
        }
        return movies;
    }
    @Override
public Optional<User> selectUserByUsername(String username) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(SELECT_USER_BY_USERNAME)) {

        stmt.setString(USERNAME, username);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt(ID_USER),
                        rs.getString(USERNAME),
                        rs.getString(PASSWORD_HASH),
                        rs.getString(ROLE)
                ));
            }
        }
    }
    return Optional.empty();
}
@Override
public int createUser(User user) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(CREATE_USER)) {

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPasswordHash());
        stmt.setString(3, user.getRole());

        stmt.registerOutParameter(4, java.sql.Types.INTEGER);

        stmt.executeUpdate();

        return stmt.getInt(4);
    }
}



}
