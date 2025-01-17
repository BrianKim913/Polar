package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata") //assign the class to the 'testdata' profile
public class BookDataLoader {
    private final BookRepository bookRepository;
    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //generate test data when ApplicationReadyEvent is sent(when application start up phase is completed)
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silvertongue", 9.90,"brian" );
        var book2 = Book.of("1234567892", "Polar Journey", "Iorek Byrnison", 12.90,"brian");
        bookRepository.saveAll(List.of(book1, book2));

    }
}
