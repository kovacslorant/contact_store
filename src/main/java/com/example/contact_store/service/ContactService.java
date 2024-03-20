package com.example.contact_store.service;

import com.example.contact_store.domain.Contact;
import com.example.contact_store.dto.contact.ContactCreateCommand;
import com.example.contact_store.repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserService userService;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserService userService) {
        this.contactRepository = contactRepository;
        this.userService = userService;
    }


    public void saveContact(ContactCreateCommand command) {
        if(contactRepository.existsByTajNumber(command.getTajNumber())
        || contactRepository.existsByTaxIdentificationNumber(command.getTaxIdentificationNumber())){
            throw new IllegalStateException("There is an contact with that TAJ or TIN number!");
        }
        Contact contact = mapContactDtoToEntity(command);
        contactRepository.save(contact);
    }

    private Contact mapContactDtoToEntity(ContactCreateCommand command) {

        Contact contact = new Contact();
        contact.setName(command.getName());
        contact.setBornDate(command.getBornDate());
        contact.setMothersName(command.getMothersName());
        contact.setTajNumber(command.getTajNumber());
        contact.setTaxIdentificationNumber(command.getTaxIdentificationNumber());
        contact.setAddresses(command.getAddresses());
        contact.setUser(userService.findById(command.getUserId()));
        contact.setPhone(command.getPhone());
        contact.setEmail(command.getEmail());

        return contact;
    }
}
