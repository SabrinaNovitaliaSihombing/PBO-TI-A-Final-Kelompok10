package libraryapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import libraryapp.config.Database;
import libraryapp.views.LibraryView;

@ComponentScan(basePackages = "libraryapp")
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        LibraryView libraryView = applicationContext.getBean(LibraryView.class);
        libraryView.run();
    }

    @Bean
    Database database() {
        Database database = new Database("library", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}