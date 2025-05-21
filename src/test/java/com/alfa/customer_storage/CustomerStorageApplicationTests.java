package com.alfa.customer_storage;

import com.alfa.customer_storage.Exception.DataException;
import com.alfa.customer_storage.Exception.RequestException;
import com.alfa.customer_storage.controller.StorageController;
import com.alfa.customer_storage.dto.ClientDto;
import com.alfa.customer_storage.dto.ContactDto;
import com.alfa.customer_storage.entity.Client;
import com.alfa.customer_storage.entity.Contact;
import com.alfa.customer_storage.repository.ClientRepository;
import com.alfa.customer_storage.repository.ContactRepository;
import com.alfa.customer_storage.service.StorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CustomerStorageApplicationTests {

	ContactRepository contactRepository = Mockito.spy(ContactRepository.class);
	ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
	StorageService storageService = new StorageService(clientRepository, contactRepository);
	StorageController storageController = new StorageController(storageService);

	@Test
	@DisplayName("Добавление клиента - OK")
	void addClientTest() {
		ClientDto clientDto = new ClientDto();
		clientDto.setName("test");
		clientDto.setLastName("test@test.com");
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientEntity);

		ResponseEntity result = storageController.addClient(clientDto);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Добавление клиента - 400")
	void addClientDataExceptionTest() {

		storageController = new StorageController(storageService);
		Assertions.assertThrows(DataException.class, () -> storageController.addClient(null));
	}

	@Test
	@DisplayName("Получение клиента - OK")
	void getClientTest() {
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));

		ResponseEntity result = storageController.getClient(1);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Получение клиента - 404 id невалидный")
	void getClientDataExceptionTest() {

		storageController = new StorageController(storageService);
		Assertions.assertThrows(DataException.class, () -> storageController.getClient(0));
	}

	@Test
	@DisplayName("Получение клиента - 404 нет клиента")
	void getClientDataExceptionNotClientTest() {

		Mockito.when(clientRepository.findById(2)).thenReturn(Optional.empty());

		Assertions.assertThrows(RequestException.class, () -> storageController.getClient(2));
	}

	@Test
	@DisplayName("Получение списка клиента - OK")
	void getAllClientTest() {
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		Client clientEntity2 = new Client("test", "test@test.com");
		clientEntity.setId(2);
		List<Client> clientList = new ArrayList<>();
		clientList.add(clientEntity);
		clientList.add(clientEntity2);
		Mockito.when(clientRepository.findAll()).thenReturn(clientList);

		ResponseEntity<List<ClientDto>> result = storageController.getAllClient();
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals(2, result.getBody().size());
	}

	@Test
	@DisplayName("Получение списка клиента - 404 нет списка")
	void getAllClientDataExceptionTest() {
		List<Client> clientList = null;
		Mockito.when(clientRepository.findAll()).thenReturn(clientList);
		Assertions.assertThrows(RequestException.class, () -> storageController.getAllClient());
	}

	@Test
	@DisplayName("Изменение клиента - OK")
	void patchClientTest() {
		ClientDto clientDto = new ClientDto();
		clientDto.setName("test");
		clientDto.setLastName("test@test.com");
		Client client = new Client("One", "one@one.com");
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(client));
		Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientEntity);

		ResponseEntity result = storageController.patchClient(1, clientDto);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Изменение клиента - 404")
	void patchClientRequestExceptionTest() {
		ClientDto clientDto = new ClientDto();
		clientDto.setName("test");
		clientDto.setLastName("test@test.com");
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());
		Assertions.assertThrows(RequestException.class, () -> storageController.patchClient(1, clientDto));
	}

	@Test
	@DisplayName("Изменение клиента - 400")
	void patchClientDataExceptionTest() {
		Assertions.assertThrows(DataException.class, () -> storageController.patchClient(1, null));
	}

	@Test
	@DisplayName("Изменение клиента целиком - OK")
	void putClientTest() {
		ClientDto clientDto = new ClientDto();
		clientDto.setName("test");
		clientDto.setLastName("test@test.com");
		Client client = new Client("One", "one@one.com");
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(client));
		Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientEntity);

		ResponseEntity result = storageController.putClient(1, clientDto);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Изменение клиента целиком - 404")
	void putClientRequestExceptionTest() {
		ClientDto clientDto = new ClientDto();
		clientDto.setName("test");
		clientDto.setLastName("test@test.com");
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());
		Assertions.assertThrows(RequestException.class, () -> storageController.putClient(1, clientDto));
	}

	@Test
	@DisplayName("Изменение клиента целиком - 400")
	void putClientDataExceptionTest() {
		Assertions.assertThrows(DataException.class, () -> storageController.patchClient(1, null));
	}

	@Test
	@DisplayName("Удаление клиента - OK")
	void deleteClientTest() {
		Contact contact = new Contact("12345678", "test@test.com");
		contact.setId(1);
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		clientEntity.setContact(contact);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));

		ResponseEntity result = storageController.deleteClient(1);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Удаление клиента - 404")
	void deleteClientRequestExceptionTest() {

		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(RequestException.class, () -> storageController.deleteClient(1));
	}

	@Test
	@DisplayName("Удаление клиента - 400")
	void deleteClientDataExceptionTest() {

		Assertions.assertThrows(DataException.class, () -> storageController.deleteClient(0));
	}

	@Test
	@DisplayName("Получение контакта - OK")
	void getContactTest() {
		Contact contact = new Contact("12345678", "test@test.com");
		contact.setId(1);
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		clientEntity.setContact(contact);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));
		Mockito.when(contactRepository.findById(1)).thenReturn(Optional.of(contact));

		ResponseEntity result = storageController.getContact(1);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Получение контакта - 404")
	void getContactRequestExceptionTest() {

		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(RequestException.class, () -> storageController.getContact(1));
	}

	@Test
	@DisplayName("Получение контакта - 400")
	void getContactDataExceptionTest() {

		Assertions.assertThrows(DataException.class, () -> storageController.getContact(0));
	}

	@Test
	@DisplayName("Добавление контакта - OK")
	void addContactTest() {
		ContactDto contactDto = new ContactDto("12345678", "test@test.com");
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));
		Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientEntity);

		ResponseEntity result = storageController.addContact(1, contactDto);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Добавление контакта - 404")
	void addContactRequestExceptionTest() {
		ContactDto contactDto = new ContactDto("12345678", "test@test.com");

		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(RequestException.class, () -> storageController.addContact(1, contactDto));
	}

	@Test
	@DisplayName("Добавление контакта - 400")
	void addContactDataExceptionTest() {

		Assertions.assertThrows(DataException.class, () -> storageController.addContact(1, null));
	}

	@Test
	@DisplayName("Изменение контакта - OK")
	void patchContactTest() {
		ContactDto contactDto = new ContactDto("12345678", "test@test.com");
		Contact contact = new Contact("123", "one@test.com");
		contact.setId(1);
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		clientEntity.setContact(contact);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));

		ResponseEntity result = storageController.patchContact(1, contactDto);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Изменение контакта - 404")
	void patchContactRequestExceptionTest() {
		ContactDto contactDto = new ContactDto("12345678", "test@test.com");
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(RequestException.class, () -> storageController.patchContact(1, contactDto));
	}

	@Test
	@DisplayName("Изменение контакта - 400")
	void patchContactDataExceptionTest() {
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(DataException.class, () -> storageController.patchContact(1, null));
	}

	@Test
	@DisplayName("Изменение контакта целиком - OK")
	void putContactTest() {
		ContactDto contactDto = new ContactDto("12345678", "test@test.com");
		Contact contact = new Contact("123", "one@test.com");
		contact.setId(1);
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		clientEntity.setContact(contact);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));

		ResponseEntity result = storageController.putContact(1, contactDto);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Изменение контакта целиком - 404")
	void putContactRequestExceptionTest() {
		ContactDto contactDto = new ContactDto("12345678", "test@test.com");
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(RequestException.class, () -> storageController.putContact(1, contactDto));
	}

	@Test
	@DisplayName("Изменение контакта целиком - 400")
	void putContactDataExceptionTest() {
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(DataException.class, () -> storageController.putContact(1, null));
	}

	@Test
	@DisplayName("Удаление контакта - OK")
	void deleteContactTest() {
		Contact contact = new Contact("12345678", "test@test.com");
		contact.setId(1);
		Client clientEntity = new Client("test", "test@test.com");
		clientEntity.setId(1);
		clientEntity.setContact(contact);
		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));

		ResponseEntity result = storageController.deleteContact(1);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Удаление контакта - 404")
	void deleteContactRequestExceptionTest() {

		Mockito.when(clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(RequestException.class, () -> storageController.deleteContact(1));
	}

	@Test
	@DisplayName("Удаление контакта - 400")
	void deleteContactDataExceptionTest() {

		Assertions.assertThrows(DataException.class, () -> storageController.deleteContact(0));
	}

}
