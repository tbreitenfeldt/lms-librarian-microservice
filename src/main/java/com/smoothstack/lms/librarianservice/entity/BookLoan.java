package com.smoothstack.lms.librarianservice.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;;

@Entity
@Table
public class BookLoan {

    @Embeddable
    public static class BookLoanId implements Serializable {

        private static final long serialVersionUID = -8848058513226751763L;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book book;

        @ManyToOne
        @JoinColumn(name = "borrower_id")
        private Borrower borrower;

        @ManyToOne
        @JoinColumn(name = "branch_id")
        private LibraryBranch branch;

        public BookLoanId() {
        }

        public BookLoanId(long bookId, long borrowerId, long branchId) {
            this.book = new Book();
            this.borrower = new Borrower();
            this.branch = new LibraryBranch();
            this.book.setId(bookId);
            this.borrower.setId(borrowerId);
            this.branch.setId(branchId);
        }

        public Book getBook() {
            return this.book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public Borrower getBorrower() {
            return this.borrower;
        }

        public void setBorrower(Borrower borrower) {
            this.borrower = borrower;
        }

        public LibraryBranch getBranch() {
            return this.branch;
        }

        public void setBranch(LibraryBranch branch) {
            this.branch = branch;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.book.getId(), this.borrower.getId(), this.branch.getId());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BookLoanId)) {
                return false;
            }
            BookLoanId other = (BookLoanId) obj;
            return Objects.equals(this.book.getId(), other.book.getId())
                    && Objects.equals(this.borrower.getId(), other.borrower.getId())
                    && Objects.equals(this.branch.getId(), other.branch.getId());
        }

        @Override
        public String toString() {
            return "BookLoanId [book=" + this.book + ", borrower=" + this.borrower + ", branch=" + this.branch + "]";
        }

    }

    @EmbeddedId
    private BookLoanId id;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOut;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dueDate;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateIn;

    public BookLoan() {
    }

    public BookLoan(BookLoanId id, LocalDate dateOut, LocalDate dueDate, LocalDate dateIn) {
        this.id = id;
        this.dateOut = dateOut;
        this.dueDate = dueDate;
        this.dateIn = dateIn;
    }

    public BookLoanId getId() {
        return this.id;
    }

    public void setId(BookLoanId id) {
        this.id = id;
    }

    public LocalDate getDateOut() {
        return this.dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDateIn() {
        return this.dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    @Override
    public String toString() {
        return "BookLoan [id=" + this.id + ", dateOut=" + this.dateOut + ", dueDate=" + this.dueDate + ", dateIn="
                + this.dateIn + "]";
    }

}
