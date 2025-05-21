package com.alfa.customer_storage.service;

import com.alfa.customer_storage.Exception.DataException;
import com.alfa.customer_storage.Exception.RequestException;
import com.alfa.customer_storage.dto.ClientDto;
import com.alfa.customer_storage.dto.ContactDto;
import com.alfa.customer_storage.entity.Client;
import com.alfa.customer_storage.entity.Contact;
import com.alfa.customer_storage.repository.ClientRepository;
import com.alfa.customer_storage.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private final ClientRepository clientRepository;

    private final ContactRepository contactRepository;

    public StorageService(ClientRepository clientRepository, ContactRepository contactRepository) {
        this.clientRepository = clientRepository;
        this.contactRepository = contactRepository;
    }

    public ClientDto addClient(ClientDto clientDto) {
        if (clientDto == null) {
            throw new DataException("Данные о клиенте не получены!");
        }
        Client client = new Client(clientDto.getName(), clientDto.getLastName());
        Client clientEntity = clientRepository.save(client);
        return new ClientDto(clientEntity.getId(), clientDto.getName(), clientEntity.getLastName());
    }

    public ClientDto patchClient(Integer id, ClientDto clientDto) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        if (clientDto == null) {
            throw new DataException("Данные о клиенте не получены!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        if (clientDto.getName() != null && !clientDto.getName().isBlank()) {
            client.get().setName(clientDto.getName());
        }
        if (clientDto.getLastName() != null && !clientDto.getLastName().isBlank()) {
            client.get().setLastName(clientDto.getLastName());
        }
        Client clientEntity = clientRepository.save(client.get());
        return new ClientDto(clientEntity.getId(), clientEntity.getName(), clientEntity.getLastName());
    }

    public ClientDto putClient(Integer id, ClientDto clientDto) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        if (clientDto == null) {
            throw new DataException("Данные о клиенте не получены!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        client.get().setName(clientDto.getName());
        client.get().setLastName(clientDto.getLastName());
        Client clientEntity = clientRepository.save(client.get());
        return new ClientDto(clientEntity.getId(), clientEntity.getName(), clientEntity.getLastName());
    }

    public void deleteClient(Integer id) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        clientRepository.deleteById(client.get().getId());
        if (client.get().getContact() != null) {
            contactRepository.deleteById(client.get().getContact().getId());
        }
    }

    public ClientDto getClient(Integer id) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        return new ClientDto(client.get().getId(), client.get().getName(), client.get().getLastName());
    }

    public List<ClientDto> getAllClient() {
        List<Client> client = clientRepository.findAll();
        if (client == null) {
            throw new RequestException("Нет клиентов!");
        }
        return client.stream().map(c -> new ClientDto(c.getId(), c.getName(), c.getLastName())).toList();
    }


    public ContactDto getContact(Integer id) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        if (client.get().getContact() == null) {
            return null;
        }
        Optional<Contact> contact = contactRepository.findById(client.get().getContact().getId());
        if (contact.isEmpty()) {
            throw new RequestException("Нет контакта!");
        }
        return new ContactDto(contact.get().getPhone(), contact.get().getEmail());
    }

    public void addContact(Integer id, ContactDto contactDto) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        if (contactDto == null) {
            throw new DataException("Данные о контакте не получены!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        if (client.get().getContact() != null) {
            throw new RequestException("Контакт уже существует!");
        }
        Contact contact = new Contact(contactDto.getPhone(), contactDto.getEmail());
        Contact contactEntity = contactRepository.save(contact);
        client.get().setContact(contactEntity);
        clientRepository.save(client.get());
    }

    public ContactDto patchContact(Integer id, ContactDto contactDto) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        if (contactDto == null) {
            throw new DataException("Данные о контакте не получены!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        Contact contact = client.get().getContact();
        if (contactDto.getPhone() != null && !contactDto.getPhone().isBlank()) {
            contact.setPhone(contactDto.getPhone());
        }
        if (contactDto.getEmail() != null && !contactDto.getEmail().isBlank()) {
            contact.setEmail(contactDto.getEmail());
        }
        contactRepository.save(contact);
        return new ContactDto(contact.getPhone(), contact.getEmail());
    }

    public ContactDto putContact(Integer id, ContactDto contactDto) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        if (contactDto == null) {
            throw new DataException("Данные о контакте не получены!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        Contact contact = client.get().getContact();
        contact.setPhone(contactDto.getPhone());
        contact.setEmail(contactDto.getEmail());
        contactRepository.save(contact);
        return new ContactDto(contact.getPhone(), contact.getEmail());
    }

    public void deleteContact(Integer id) {
        if (id <= 0) {
            throw new DataException("Неверный id пользователя!");
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RequestException("Нет клиента с таким id!");
        }
        Integer idContact = client.get().getContact().getId();
        client.get().setContact(null);
        clientRepository.save(client.get());
        contactRepository.deleteById(idContact);
    }

}
