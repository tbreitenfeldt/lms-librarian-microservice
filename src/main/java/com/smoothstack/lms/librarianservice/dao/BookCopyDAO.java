package com.smoothstack.lms.librarianservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.librarianservice.entity.BookCopy;
import com.smoothstack.lms.librarianservice.entity.BookCopy.BookCopyId;

@Repository
public interface BookCopyDAO extends JpaRepository<BookCopy, BookCopyId> {

    @Query("FROM BookCopy bc WHERE bc.id.branch.id = :branchId")
    public List<BookCopy> findBookCopiesById(@Param("branchId") Long branchId);

}
