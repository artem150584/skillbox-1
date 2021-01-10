package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.example.web.dto.BookPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("removedBook", new BookPattern());
        model.addAttribute("bookPattern", new BookPattern());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        logger.info("Current repository contents: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove, Model model) {
        if(bookService.removeBookById(bookIdToRemove)) {
            return "redirect:/books/shelf";
        } else {
            return books(model);
        }
    }

    @PostMapping("/remove_pattern")
    public String removeBook(BookPattern removedBook, Model model) {
        if(bookService.removeBookByPattern(removedBook)) {
            return "redirect:/books/shelf";
        } else {
            return books(model);
        }
    }

    @PostMapping("/filter")
    public String filterBook(BookPattern bookPattern, Model model) {
        logger.info("got book shelf with filter: " + bookPattern);
        model.addAttribute("book", new Book());
        model.addAttribute("removedBook", new BookPattern());
        model.addAttribute("bookPattern", new BookPattern());
        model.addAttribute("bookList", bookService.getFilteredBooks(bookPattern));
        return "book_shelf";
    }
}
