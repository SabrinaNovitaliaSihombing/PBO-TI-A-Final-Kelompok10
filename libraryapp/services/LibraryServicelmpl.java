package libraryapp.services;

import libraryapp.entities.Library;
import libraryapp.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class LibraryServicelmpl implements LibraryService {
    private LibraryRepository libraryRepository;
    @Autowired
    public LibraryServicelmpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Library[] getLibrary() {
        return libraryRepository.getAll();
    }

    @Override
    public void addLibrary(final String buku) {
        if (buku.isBlank() || buku.isEmpty()) {
            System.out.println("Masukkan buku dengan benar");
            return;
        }
        Library library = new Library();
        library.setLibrary(buku);
        libraryRepository.add(library);
    }

    @Override
    public Boolean removeLibrary(final Integer number) {
        return libraryRepository.remove(number);
    }

    @Override
    public Boolean editLibrary(final Integer number, String buku) {
        Library library = new Library();
        library.setLibrary(buku);
        library.setId(number);
        return libraryRepository.edit(library);
    }
}
