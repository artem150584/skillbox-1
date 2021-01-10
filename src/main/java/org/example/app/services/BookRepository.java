package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.example.web.dto.RemovedBook;
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
    public boolean removeItemsByPattern(RemovedBook removedBook) {
        List<Book> booksToRemove = new ArrayList<>();

        String authorPattern = removedBook.getAuthorPattern();
        String titlePattern = removedBook.getTitlePattern();
        String sizePattern = removedBook.getSizePattern();

        boolean isAnyFieldFilled = !(authorPattern.isEmpty() && titlePattern.isEmpty() && sizePattern.isEmpty());

        for (Book book : reteiveAll()) {
            if (isAnyFieldFilled &&
                    isRegExpFound(book.getAuthor(), authorPattern) &&
                    isRegExpFound(book.getTitle(), titlePattern) &&
                    isRegExpFound(String.format("%d", book.getSize()), sizePattern)) {
                logger.info("remove book completed: " + book);
                booksToRemove.add(book);
            }
        }

        return repo.removeAll(booksToRemove);
    }

    private boolean isRegExpFound(String field, String pattern) {
        Pattern ptrn = Pattern.compile(pattern);
        Matcher matcher = ptrn.matcher(field);

        return matcher.find();
    }
}
