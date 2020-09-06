package com.smoothstack.lms.librarianservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.librarianservice.entity.Publisher;

@Repository
public interface PublisherDAO extends JpaRepository<Publisher, Long> {

}
