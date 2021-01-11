package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.example.web.dto.BookPattern;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public List<Book> retrieveFiltered(BookPattern bookPattern) {
        List<Book> filteredBooks = new ArrayList<>();

        String authorPattern = bookPattern.getAuthorPattern();
        String titlePattern = bookPattern.getTitlePattern();
        String sizePattern = bookPattern.getSizePattern();

        boolean isAnyFieldFilled = !(authorPattern.isEmpty() && titlePattern.isEmpty() && sizePattern.isEmpty());

        for (Book book : retrieveAll()) {
            String bookSize = book.getSize() == null ? "" : book.getSize().toString();
            if (isAnyFieldFilled &&
                    book.getAuthor().matches(authorPattern) &&
                    book.getTitle().matches(titlePattern) &&
                    bookSize.matches(sizePattern)) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

    @Override
    public void store(Book book) {
        if (book.getAuthor().isEmpty() && book.getTitle().isEmpty() && book.getSize() == null) {
            logger.info("can not store book: all fields are empty");
        } else {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
        }
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retrieveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemsByPattern(BookPattern removedBook) {
        List<Book> booksToRemove = retrieveFiltered(removedBook);
        logger.info("remove book(s) completed: " + booksToRemove);
        return repo.removeAll(booksToRemove);
    }
}
