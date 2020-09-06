package com.smoothstack.lms.librarianservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.librarianservice.entity.Book;
import com.smoothstack.lms.librarianservice.entity.BookCopy;
import com.smoothstack.lms.librarianservice.entity.BookCopy.BookCopyId;
import com.smoothstack.lms.librarianservice.entity.LibraryBranch;
import com.smoothstack.lms.librarianservice.service.LibrarianService;

@RestController
@RequestMapping("/lms/librarian")
public class LibrarianController {

    private static final Logger logger = LogManager.getLogger(LibrarianController.class);

    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/book-copies")
    public ResponseEntity<BookCopy> createBookCopy(@RequestBody @Valid BookCopy bookCopy) {
        logger.debug("request: {}", bookCopy.toString());
        BookCopy response = this.librarianService.createBookCopy(bookCopy);
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<BookCopy>(response, HttpStatus.CREATED);
    }

    @GetMapping("/books/book-copies/branches/{branchId}")
    public ResponseEntity<List<Book>> getBooksNotInBookCopies(@PathVariable @Min(1) Long branchId) {
        List<Book> response = this.librarianService.getBooksNotInBookCopies(branchId);
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<List<Book>>(response, HttpStatus.OK);
    }

    @GetMapping("/branches")
    public ResponseEntity<List<LibraryBranch>> getLibraryBranches() {
        List<LibraryBranch> response = this.librarianService.getLibraryBranches();
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<List<LibraryBranch>>(response, HttpStatus.OK);
    }

    @GetMapping("/branches/{branchId}")
    public ResponseEntity<LibraryBranch> getLibraryBranchById(@PathVariable @Min(1) Long branchId) {
        LibraryBranch response = this.librarianService.getLibraryBranchById(branchId);
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<LibraryBranch>(response, HttpStatus.OK);
    }

    @GetMapping("/book-copies/branches/{branchId}")
    public ResponseEntity<List<BookCopy>> getBookCopies(@PathVariable @Min(1) Long branchId) {
        logger.debug("request: {}", branchId.toString());
        List<BookCopy> response = this.librarianService.getBookCopiesById(branchId);
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<List<BookCopy>>(response, HttpStatus.OK);
    }

    @PutMapping("/book-copies/books/{bookId}/branches/{branchId}")
    public ResponseEntity<BookCopy> updateBookCopy(@PathVariable @Min(1) Long bookId,
            @PathVariable @Min(1) Long branchId, @RequestBody @Valid BookCopy bookCopy) {
        bookCopy.setId(new BookCopyId(bookId, branchId));
        logger.debug("request: {}", bookCopy.toString());
        BookCopy response = this.librarianService.updateBookCopy(bookCopy);
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<BookCopy>(response, HttpStatus.OK);
    }

    @PutMapping("/branches/{branchId}")
    public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable @Min(1) Long branchId,
            @RequestBody LibraryBranch branch) {
        branch.setId(branchId);
        logger.debug("request: {}", branch.toString());
        LibraryBranch response = this.librarianService.updateLibraryBranch(branch);
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<LibraryBranch>(response, HttpStatus.OK);
    }

    @DeleteMapping("/book-copies/books/{bookId}/branches/{branchId}")
    public ResponseEntity<BookCopy> removeBookCopy(@PathVariable @Min(1) Long bookId,
            @PathVariable @Min(1) Long branchId) {
        logger.debug("bookId: {}, branchId: {}", bookId, branchId);
        this.librarianService.removeBookCopy(new BookCopyId(bookId, branchId));
        return new ResponseEntity<BookCopy>(HttpStatus.NO_CONTENT);
    }

}
