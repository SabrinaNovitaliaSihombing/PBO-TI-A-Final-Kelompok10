package repositories;

import entities.Library;

public class LibraryRepositorylmpl implements LibraryRepository{
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
        Boolean isFull = true;
        isFull = isArrayFull(isFull);

        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    private void resizeArrayToTwoTimesBigger() {
        Library[] temp = libraries;
        libraries = new Library[][libraries.length * 2];
        for (int i = 0; i < temp.length; i++) {
            libraries[i] = temp[i];
        }
    }

    private Boolean isArrayFull(Boolean isFull) {
        for (int i = 0; i < libraries.length; i++) {
            if (libraries[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    @Override
    public Boolean remove(final Integer number) {
        if (isSelectedLibraryNotValid(number)) {
            return false;
        }

        for (int i = number - 1; i < libraries.length; i++) {
            if (i == (libraries.length - 1)) {
                libraries[i] = null;
            } else {
                libraries[i] = libraries[i + 1];
            }
        }
        return true;
    }
    private static boolean isSelectedLibraryNotValid(final Integer number) {
        if (number <= 0) {
            return true;
        }

        if (number - 1 > libraries.length - 1) {
            return true;
        }

        if (libraries[number - 1] == null) {
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


