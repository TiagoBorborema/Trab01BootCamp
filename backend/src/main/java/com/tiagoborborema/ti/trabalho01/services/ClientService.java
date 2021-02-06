package com.tiagoborborema.ti.trabalho01.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiagoborborema.ti.trabalho01.dto.ClientDTO;
import com.tiagoborborema.ti.trabalho01.entities.Client;
import com.tiagoborborema.ti.trabalho01.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly=true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
	Page<Client> list = repository.findAll(pageRequest);
	
	//no modelo de expressão lambda fica mais fácil implementar.
	return list.map(x -> new ClientDTO(x));
	
	/*List<ClientDTO> lstDto = new ArrayList<>();
	for(Client cli : list) {
		lstDto.add(new ClientDTO(cli));		
	}*/
	
	}
	
	@Transactional(readOnly=true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client  entity =   obj.get(); //obj.orElseThrow(()-> new ResouserceNotFoundException("Client not found"));
		return new ClientDTO(entity);
	}

}
