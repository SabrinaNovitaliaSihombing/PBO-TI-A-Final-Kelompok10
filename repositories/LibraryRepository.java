package repositories;

import entities.Library;

public interface LibraryRepository {
    Library[] getAll();

    void add(Library library);

    Boolean remove(Integer id);

    Boolean edit(Library library);
}