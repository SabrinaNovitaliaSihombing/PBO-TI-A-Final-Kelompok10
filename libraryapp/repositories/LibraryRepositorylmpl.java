package libraryapp.repositories;

import libraryapp.entities.Library;

public class LibraryRepositorylmpl implements LibraryRepository {

    public static Library[] libraries = new Library[2];

    @Override
    public Library[] getAll() {
        return libraries;
    }

    @Override
    public void add(final Library library) {
        resizeArrayIfFull();

        for (int i = 0; i < libraries.length; i++) {
            if (libraries[i] == null) {
                libraries[i] = library;
                break;
            }
        }
    }

    private void resizeArrayIfFull() {
        if (isArrayFull()) {
            resizeArrayToTwoTimesBigger();
        }
    }

    private void resizeArrayToTwoTimesBigger() {
        Library[] temp = libraries;
        libraries = new Library[libraries.length * 2];
        System.arraycopy(temp, 0, libraries, 0, temp.length);
    }

    private boolean isArrayFull() {
        for (Library library : libraries) {
            if (library == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean remove(final Integer number) {
        if (isSelectedLibraryNotValid(number)) {
            return false;
        }

        for (int i = number - 1; i < libraries.length - 1; i++) {
            libraries[i] = libraries[i + 1];
        }

        libraries[libraries.length - 1] = null;  // Clear the last element
        return true;
    }

    private static boolean isSelectedLibraryNotValid(final Integer number) {
        if (number <= 0 || number > libraries.length || libraries[number - 1] == null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean edit(final Library library) {
        if (isSelectedLibraryNotValid(library.getId())) {
            return false;
        }
        libraries[library.getId() - 1] = library;
        return true;
    }
}