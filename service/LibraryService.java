package service;

import entities.Library;

public interface LibraryService {
    Library[] getLibrary();
    void addLibrary(String buku);
    Boolean removeLibrary(Integer number);
    Boolean editLibrary(Integer number, String buku);
}
