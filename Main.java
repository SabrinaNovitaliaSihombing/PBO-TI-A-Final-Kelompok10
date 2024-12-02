import repositories.LibraryRepository;
import repositories.LibraryRepositorylmpl;
import services.LibraryService;
import services.LibraryServicelmpl;
import views.LibraryTerminalViewlmpl;
import views.LibraryView;

public class Main {
    public static void main(String[] args) {
        LibraryRepository libraryRepository = new LibraryRepositorylmpl();
        LibraryService libraryService = new LibraryServicelmpl(libraryRepository);
        LibraryView libraryView = new LibraryTerminalViewlmpl(libraryService);
        libraryView.run();
    }
}
