package libraryapp.repositories;

import libraryapp.config.Database;
import libraryapp.entities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryRepositoryDbImpl implements LibraryRepository {
    private final Database database;

    @Autowired
    public LibraryRepositoryDbImpl(final Database database) {
        this.database = database;
    }

    @Override
    public Library[] getAll() {
        String sqlStatement = "SELECT * FROM books";
        List<Library> libraries = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Library library = new Library();
                library.setId(resultSet.getInt("id"));
                library.setLibrary(resultSet.getString("buku"));
                libraries.add(library);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return libraries.toArray(new Library[0]);
    }

    @Override
    public void add(Library library) {
        String sqlStatement = "INSERT INTO books (books) VALUES (?)";
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, library.getLibrary());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert successful!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean remove(Integer id) {
        String sqlStatement = "DELETE FROM books WHERE id = ?";
        if (getDbId(id) == null) {
            return false;
        }
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Delete successful!");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean edit(Library library) {
        String sqlStatement = "UPDATE books SET books = ? WHERE id = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, library.getLibrary());
            preparedStatement.setInt(2, library.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful!");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private Integer getDbId(Integer id) {
        Library[] libraries = getAll();
        if (id == null || id < 1 || id > libraries.length) {
            return null;
        }
        return libraries[id - 1].getId();
    }
}
