package com.csci318project.onlineordering.repository;

import com.csci318project.onlineordering.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
