package business;

import entity.Movie;
import util.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieBusiness {
    public static Movie getMoviesByName(String name) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Movie movie = null;

        try {
            conn = ConnectionDatabase.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM movies WHERE title = ?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDatabase.closeConnection(conn);
        }

        return movie;
    }
    public static List<Movie> getMovies() {
        Connection conn = null;
        Statement stmt = null;
        List<Movie> movies = new ArrayList<Movie>();

        try {
            conn = ConnectionDatabase.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from movies";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDatabase.closeConnection(conn);
        }
        ResultSet rs = null;
        return movies;
    }

    public static Movie getMovie(int id) {
        return null;
    }

    public static boolean addMovie(Movie movie) {
        Connection  conn = null;
        CallableStatement callableStatement = null;

        try {
            conn = ConnectionDatabase.getConnection();
            callableStatement = conn.prepareCall("call add_movie(?,?,?)");
            callableStatement.setString(1, movie.getTitle());
            callableStatement.setString(2, movie.getDirector());
            callableStatement.setInt(3, movie.getYear());
            return callableStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(conn);
        }
        return false;
    }

    public static boolean updateMovie(Movie movie) {
        return false;
    }

    public static boolean deleteMovie(int id) {
        return false;
    }
}
