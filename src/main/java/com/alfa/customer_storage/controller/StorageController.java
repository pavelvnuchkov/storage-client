package com.alfa.customer_storage.controller;

import com.alfa.customer_storage.dto.ClientDto;
import com.alfa.customer_storage.dto.ContactDto;
import com.alfa.customer_storage.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Storage controller", description = "Управление хранилищем клиентов и контактов")
public class StorageController {

    private final StorageService service;

    public StorageController(StorageService service) {
        this.service = service;
    }

    @PostMapping("/client")
    @Operation(summary = "Добавление клиента", description = "Позволяет добавить нового клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(service.addClient(clientDto), HttpStatus.OK);
    }

    @GetMapping("/client")
    @Operation(summary = "Получение данных клиента", description = "Позволяет получить данные клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity<ClientDto> getClient(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(service.getClient(id), HttpStatus.OK);
    }

    @GetMapping("/client/all")
    @Operation(summary = "Получение данных о всех клиентах", description = "Позволяет получить данные о всех клиентах")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity<List<ClientDto>> getAllClient() {
        return new ResponseEntity<>(service.getAllClient(), HttpStatus.OK);
    }

    @PatchMapping("/client")
    @Operation(summary = "Изменение данных клиента", description = "Позволяет изменить данные клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity<ClientDto> patchClient(@RequestParam("id") Integer id, @RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(service.patchClient(id, clientDto), HttpStatus.OK);
    }

    @PutMapping("/client")
    @Operation(summary = "Изменение всех данных клиента", description = "Позволяет изменить все данные клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity<ClientDto> putClient(@RequestParam("id") Integer id, @RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(service.putClient(id, clientDto), HttpStatus.OK);
    }

    @DeleteMapping("/client")
    @Operation(summary = "Удаление клиента", description = "Позволяет удалить клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity deleteClient(@RequestParam Integer id) {
        service.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/client/contact")
    @Operation(summary = "Получение контакта", description = "Позволяет получить контакт клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity<ContactDto> getContact(@RequestParam Integer id) {
        return new ResponseEntity<>(service.getContact(id), HttpStatus.OK);
    }

    @PostMapping("/client/contact")
    @Operation(summary = "Добавление контакта", description = "Позволяет добавить контакт клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity addContact(@RequestParam("id") Integer id, @RequestBody ContactDto contactDto) {
        service.addContact(id, contactDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/client/contact")
    @Operation(summary = "Изменение контакта", description = "Позволяет изменить контакт клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity<ContactDto> patchContact(@RequestParam("id") Integer id, @RequestBody ContactDto contactDto) {
        return new ResponseEntity<>(service.patchContact(id, contactDto), HttpStatus.OK);
    }

    @PutMapping("/client/contact")
    @Operation(summary = "Изменение всего контакта", description = "Позволяет изменить весь контакт клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity<ContactDto> putContact(@RequestParam("id") Integer id, @RequestBody ContactDto contactDto) {
        return new ResponseEntity<>(service.putContact(id, contactDto), HttpStatus.OK);
    }

    @DeleteMapping("/client/contact")
    @Operation(summary = "Удаление контакта", description = "Позволяет удалить контакт клиента")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Нет данных в базе")
    public ResponseEntity deleteContact(@RequestParam Integer id) {
        service.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
