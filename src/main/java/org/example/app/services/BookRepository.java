package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.example.web.dto.BookPattern;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> reteiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public List<Book> reteiveFiltered(BookPattern bookPattern) {
        List<Book> filteredBooks = new ArrayList<>();

        String authorPattern = bookPattern.getAuthorPattern();
        String titlePattern = bookPattern.getTitlePattern();
        String sizePattern = bookPattern.getSizePattern();

        boolean isAnyFieldFilled = !(authorPattern.isEmpty() && titlePattern.isEmpty() && sizePattern.isEmpty());

        for (Book book : reteiveAll()) {
            if (isAnyFieldFilled &&
                    isPatternFound(book.getAuthor(), authorPattern) &&
                    isPatternFound(book.getTitle(), titlePattern) &&
                    isPatternFound(String.format("%d", book.getSize()), sizePattern)) {
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
        for (Book book : reteiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemsByPattern(BookPattern removedBook) {
        List<Book> booksToRemove = reteiveFiltered(removedBook);
        logger.info("remove book(s) completed: " + booksToRemove);
        return repo.removeAll(booksToRemove);
    }

    private boolean isPatternFound(String field, String pattern) {
        Pattern ptrn = Pattern.compile(pattern);
        Matcher matcher = ptrn.matcher(field);

        return matcher.find();
    }
}
