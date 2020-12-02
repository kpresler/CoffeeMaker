package SpringTransactions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import SpringTransactions.services.BookService;
import SpringTransactions.services.CompositeService;
import SpringTransactions.services.UserService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class)
                .web(WebApplicationType.NONE)
                .properties("logging.level.org.springframework.transaction=TRACE")
                .run(args);
    }

    @Bean
    public CommandLineRunner run(CompositeService compositeService, UserService userService, BookService bookService) {
        return args -> {
            try {
                compositeService.saveUserAndBook();
            } catch (RuntimeException e) {
                System.err.println("Exception: " + e);
            }

            System.out.println("All users: " + userService.findAll());
            System.out.println("All books: " + bookService.findAll());
        };
    }
}