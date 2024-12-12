package views;

import entities.Library;

import java.util.Scanner;

import services.LibraryService;

public class LibraryTerminalViewlmpl implements LibraryView {
    public static Scanner scanner = new Scanner(System.in);
    private final LibraryService libraryService;

    public LibraryTerminalViewlmpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public String input(String info) {
        System.out.print(info + " : ");
        var data = scanner.nextLine();
        return data;
    }

    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showLibrary();
            System.out.println("Welcome to Library App!");
            System.out.println("MENU : ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("Pilih");

            switch (selectedMenu) {
                case "1":
                    showMenuAddLibrary();
                    break;
                case "2":
                    showMenuRemoveLibrary();
                    break;
                case "3":
                    showMenuEditLibrary();
                    break;
                case "4":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu dengan benar");
            }
        }
    }

    public void showMenuRemoveLibrary() {
        System.out.println("Menghapus Daftar Buku");
        var number = input("Buku yang ingin dihapus (x jika batal)");
        if (number.equals("x")) {
        } else {
            boolean success = libraryService.removeLibrary(Integer.valueOf(number));
            if (!success) {
                System.out.println("Gagal menghapus library : " + number);
            }
        }
    }

    public void showMenuAddLibrary() {
        System.out.println("Menambah Daftar Buku");
        var buku = input("Buku (x jika batal)");
        if (buku.equals("x")) {
        } else {
            libraryService.addLibrary(buku);
        }
    }

    public void showMenuEditLibrary() {
        System.out.println("Mengedit Daftar Buku");
        String selectedLibrary = input("Masukkan daftar buku (x jika batal)");
        if (selectedLibrary.equals("x")) {
            return;
        }
        String newLibrary = input("Masukkan daftar buku yang baru (x jika batal)");
        if (newLibrary.equals("x")) {
            return;
        }
        boolean isEditLibrarySuccess = libraryService.editLibrary(Integer.valueOf(selectedLibrary), newLibrary);
        if (isEditLibrarySuccess) {
            System.out.println("Berhasil mengedit buku");
        } else {
            System.out.println("Gagal mengedit buku");
        }
    }

    public void showLibrary() {
        System.out.println("Library");
        Library[] library = libraryService.getLibrary();
        for (var i = 0; i < library.length; i++) {
            var buku = library[i];
            if (buku != null) {
                System.out.println((i + 1) + ". " + buku.getId());
            }
        }
    }

    @Override
    public void run() {
        showMainMenu();
    }
}
