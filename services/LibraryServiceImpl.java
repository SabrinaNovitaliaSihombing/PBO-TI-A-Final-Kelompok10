package services;

import entities.Library;

public class LibraryServiceImpl implements LibraryService {
    private LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
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
