package org.example.app.services;

import org.example.web.dto.Book;
import org.example.web.dto.BookPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public List<Book> getFilteredBooks(BookPattern removedBook) {
        return bookRepo.retrieveFiltered(removedBook);
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public boolean removeBookByPattern(BookPattern removedBook) {
        return bookRepo.removeItemsByPattern(removedBook);
    }
}
