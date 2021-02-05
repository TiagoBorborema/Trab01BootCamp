package com.tiagoborborema.ti.trabalho01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiagoborborema.ti.trabalho01.entities.Client;
import com.tiagoborborema.ti.trabalho01.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly=true)
	public List<Client> findAll(){
		return repository.findAll();
	}
}
