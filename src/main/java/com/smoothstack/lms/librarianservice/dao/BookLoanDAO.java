package com.smoothstack.lms.librarianservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.librarianservice.entity.BookLoan;
import com.smoothstack.lms.librarianservice.entity.BookLoan.BookLoanId;

@Repository
public interface BookLoanDAO extends JpaRepository<BookLoan, BookLoanId> {

}
