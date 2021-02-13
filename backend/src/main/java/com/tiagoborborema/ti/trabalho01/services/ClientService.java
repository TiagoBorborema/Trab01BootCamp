package com.tiagoborborema.ti.trabalho01.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiagoborborema.ti.trabalho01.dto.ClientDTO;
import com.tiagoborborema.ti.trabalho01.entities.Client;
import com.tiagoborborema.ti.trabalho01.repositories.ClientRepository;
import com.tiagoborborema.ti.trabalho01.services.exceptions.ResourceNotFoundException;

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
		Client  entity = obj.orElseThrow(()-> new ResourceNotFoundException("Client not found"));
		return new ClientDTO(entity);
	}	
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {	
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		
		try {
			Client entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		}
		
		catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Client not found");
		}
	}
	
	@Transactional
	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

	public void delete(Long id) {
     repository.deleteById(id);
		
	}

}
