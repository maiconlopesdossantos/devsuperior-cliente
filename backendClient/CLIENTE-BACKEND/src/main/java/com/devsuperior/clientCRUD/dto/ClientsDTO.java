package com.devsuperior.clientCRUD.dto;

import com.devsuperior.clientCRUD.entities.Client;
import com.devsuperior.clientCRUD.entities.Clients;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientsDTO implements Serializable {
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Instant birthDate;
    private Integer children;

    private List<ClientDTO> categories = new ArrayList<>();

    public ClientsDTO() {
    }

    public ClientsDTO(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientsDTO(Clients entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public ClientsDTO(Clients entity, Set<Client> categories) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new ClientDTO(cat)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

}
