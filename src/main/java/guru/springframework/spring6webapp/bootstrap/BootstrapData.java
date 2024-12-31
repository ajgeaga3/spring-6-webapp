package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author tk = new Author();
        tk.setFirstName("Tite");
        tk.setLastName("Kubo");

        Book b = new Book();
        b.setTitle("Bleach");
        b.setIsbn("123456");

        Author tkSaved = authorRepository.save(tk);
        Book bSaved = bookRepository.save(b);


        Author eo = new Author();
        eo.setFirstName("Eiichiro");
        eo.setLastName("Oda");

        Book op = new Book();
        op.setTitle("One Piece");
        op.setIsbn("654321");

        Author eoSaved = authorRepository.save(eo);
        Book opSaved = bookRepository.save(op);

        tkSaved.getBooks().add(bSaved);
        eoSaved.getBooks().add(opSaved);

        authorRepository.save(tkSaved);
        authorRepository.save(eoSaved);


        //creates NullPointerException,
        // Cannot invoke "java.util.Set.add(Object)"
        // because the return value of "guru.springframework.spring6webapp.domain.Author.getBooks()" is null
        // need to set the Set<Book> and Set<Author> = new HashSet<>();

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());






    }
}
