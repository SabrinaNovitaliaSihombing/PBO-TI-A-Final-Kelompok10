import config.Database;
import repositories.LibraryRepository;
import repositories.LibraryRepositorylmpl;
import services.LibraryService;
import services.LibraryServicelmpl;
import views.LibraryTerminalViewlmpl;
import views.LibraryView;

public class Main {
    public static void main(String[] args) {

        Database database = new Database("library", "root", "", "localhost", "3306");
        database.setup();

        LibraryRepository libraryListRepository = new LibraryRepositorylmpl(database);
        LibraryService libraryService = new LibraryServicelmpl(libraryListRepository);
        LibraryView libraryView = new LibraryTerminalViewlmpl(libraryService);

    }
}