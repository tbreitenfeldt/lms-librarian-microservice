package com.smoothstack.lms.librarianservice;

import com.smoothstack.lms.librarianservice.entity.Author;
import com.smoothstack.lms.librarianservice.entity.Book;
import com.smoothstack.lms.librarianservice.entity.BookCopy;
import com.smoothstack.lms.librarianservice.entity.BookCopy.BookCopyId;
import com.smoothstack.lms.librarianservice.entity.Genre;
import com.smoothstack.lms.librarianservice.entity.LibraryBranch;
import com.smoothstack.lms.librarianservice.entity.Publisher;

public class LibrarianEntityFactory {

    public static LibraryBranch createLibraryBranch(Long id, String name, String address) {
        LibraryBranch branch = new LibraryBranch();
        branch.setId(id);
        branch.setName(name);
        branch.setAddress(address);
        return branch;
    }

    public static BookCopy createBookCopy(Long bookId, Long branchId, Long amount) {
        BookCopy bookCopy = new BookCopy();
        BookCopyId bookCopyId = createBookCopyId(bookId, branchId);
        bookCopy.setId(bookCopyId);
        bookCopy.setAmount(amount);
        return bookCopy;
    }

    public static BookCopyId createBookCopyId(Long bookId, Long branchId) {
        BookCopyId bookCopyId = new BookCopyId();
        Book book = new Book();
        LibraryBranch branch = new LibraryBranch();
        book.setId(bookId);
        branch.setId(branchId);
        bookCopyId.setBook(book);
        bookCopyId.setBranch(branch);
        return bookCopyId;
    }

    public static Book createBook(Long id, String title, Long publisherId, Long[] authorIds, Long[] genreIds) {
        Book book = new Book();
        Publisher publisher = new Publisher();

        publisher.setId(publisherId);
        book.setTitle(title);
        book.setPublisher(publisher);
        book.setId(id);
        for (Long authorId : authorIds) {
            Author author = new Author();
            author.setId(authorId);
            book.addAuthor(author);
        }
        for (Long genreId : genreIds) {
            Genre genre = new Genre();
            genre.setId(genreId);
            book.addGenre(genre);
        }

        return book;
    }

}
