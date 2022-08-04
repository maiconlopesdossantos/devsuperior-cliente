package com.devsuperior.clientCRUD.services;

import com.devsuperior.clientCRUD.dto.ClientsDTO;
import com.devsuperior.clientCRUD.entities.Clients;
import com.devsuperior.clientCRUD.repositories.ClientsRepository;
import com.devsuperior.clientCRUD.services.exceptions.DatabaseException2;
import com.devsuperior.clientCRUD.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientsDTO> findAllPaged(PageRequest pageRequest) {
        Page<Clients> list = repository.findAll(pageRequest);
        return list.map(ClientsDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientsDTO findById(Long id) {
        Optional<Clients> obj = repository.findById(id);
        Clients entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClientsDTO(entity);
    }

    @Transactional
    public ClientsDTO insert(ClientsDTO dto) {
        Clients entity = new Clients();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientsDTO(entity);
    }

    @Transactional
    public ClientsDTO update(Long id, ClientsDTO dto) {
        try {
            Clients entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientsDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }

    }



    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException2("Integrity violation");
        }
    }
    private void copyDtoToEntity(ClientsDTO dto, Clients entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
