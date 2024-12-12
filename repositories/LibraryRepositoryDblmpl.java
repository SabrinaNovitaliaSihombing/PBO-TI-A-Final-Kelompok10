package repositories;

import config.Database;
import entities.Library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@repositories.Component
public class LibraryRepositoryDblmpl implements LibraryRepository {
    private final Database database;

    @repositories.Autowired
    public LibraryRepositoryDblmpl(final Database database) {
        this.database = database;
    }

    @Override
    public Library[] getAll() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM buku";
        List<Library> libraries = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Library library= new Library();
                Integer id = resultSet.getInt(1);
                String buku = resultSet.getString(2);
                library.setId(id);
                library.setLibrary(buku);
                libraries.add(library);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return libraries.toArray(Library[]::new);
    }

    @Override
    public void add(final Library library) {
        String sqlStatement = "INSERT INTO buku(buku) values(?)";
        Connection conn = database.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, library.getLibrary());

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Insert successful !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean remove(final Integer id) {
        String sqlStatement = "DELETE FROM buku WHERE id = ?";
        Connection conn = database.getConnection();
        var dbId = getDbId(id);
        if (dbId == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, dbId);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Delete successful !");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Integer getDbId(final Integer id) {
        Library[] libraries = getAll();
        Long countElement = Arrays.stream(libraries).filter(Objects::nonNull).count();
        if (countElement.intValue() == 0) {
            return null;
        }
        var dbId = libraries[id - 1].getId();
        return dbId;
    }

    @Override
    public Boolean edit(final Library library) {
        String sqlStatement = "UPDATE buku set buku= ? WHERE id = ?";
        Connection conn = database.getConnection();
        var dbId = getDbId(library.getId());
        if (dbId == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, library.getLibrary());
            preparedStatement.setInt(2, dbId);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Update successful !");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}