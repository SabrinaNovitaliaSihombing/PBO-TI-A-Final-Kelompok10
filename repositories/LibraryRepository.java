package repositories;

import entities.Library;

public class LibraryRepository {
    Library[] getAll();

    void add(Library library);

    Boolean remove(Integer Id);

    Boolean edit(Library library);
}
