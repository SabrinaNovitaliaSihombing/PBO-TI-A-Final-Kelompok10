package repositories;

import entities.Library;

public class LibraryRepositorylmpl implements LibraryRepository {
    public static Library[] libraries = new Library[2];

    @Override
    public Library[] getAll() {return libraries;}

    @Override
    public void add(final Library library) {

        resizeArrayIfFull();

        for (int I = 0; I < libraries.length; I++) {
            if (libraries[I] == null) {
                libraries[I] = library;
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
        libraries = new Library[libraries.length * 2];

        for (int I = 0; I < temp.length; I++) {
            libraries[I] = temp[I];
        }
    }

    private Boolean isArrayFull(Boolean isFull) {
        for (int I = 0; I < libraries.length; I++) {
            if (libraries[I] == null) {
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

        for (int I = number - 1; I < libraries.length; I++) {

            if (I == (libraries.length - 1)) {
                libraries[I] = null;
            } else {
                libraries[I] = libraries[I + 1];
            }
        }
        return true;
    }

    private static Boolean isSelectedLibraryNotValid(final Integer number) {
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