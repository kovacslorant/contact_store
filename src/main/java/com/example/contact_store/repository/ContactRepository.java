package com.example.contact_store.repository;

import com.example.contact_store.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Long> {

    boolean existsByTajNumber(Integer tajNumber);
    boolean existsByTaxIdentificationNumber(Integer taxIdentificationNumber);

    Contact findByTajNumber(Integer tajNumber);
    Contact findByTaxIdentificationNumber(Integer taxIdentificationNumber);
}
