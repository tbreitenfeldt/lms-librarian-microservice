package com.smoothstack.lms.librarianservice.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table
public class BookCopy {

    @Embeddable
    public static class BookCopyId implements Serializable {

        private static final long serialVersionUID = 22619397635869180L;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book book;

        @ManyToOne
        @JoinColumn(name = "branch_id")
        private LibraryBranch branch;

        public BookCopyId() {
        }

        public BookCopyId(Long bookId, Long branchId) {
            this.book = new Book();
            this.branch = new LibraryBranch();
            this.book.setId(bookId);
            this.branch.setId(branchId);
        }

        public Book getBook() {
            return this.book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public LibraryBranch getBranch() {
            return this.branch;
        }

        public void setBranch(LibraryBranch branch) {
            this.branch = branch;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.book.getId(), this.branch.getId());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BookCopyId)) {
                return false;
            }
            BookCopyId other = (BookCopyId) obj;
            return Objects.equals(this.book.getId(), other.book.getId())
                    && Objects.equals(this.branch.getId(), other.branch.getId());
        }

        @Override
        public String toString() {
            return "BookCopyId [book=" + this.book + ", branch=" + this.branch + "]";
        }

    }

    @EmbeddedId
    private BookCopyId id;

    @Column
    @Min(0)
    private Long amount;

    public BookCopyId getId() {
        return this.id;
    }

    public void setId(BookCopyId id) {
        this.id = id;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BookCopy [id=" + this.id + ", amount=" + this.amount + "]";
    }

}
