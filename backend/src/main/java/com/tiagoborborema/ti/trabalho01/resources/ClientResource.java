package com.tiagoborborema.ti.trabalho01.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagoborborema.ti.trabalho01.entities.Client;


@RestController
@RequestMapping(name="/clients")
public class ClientResource {
	
	public ResponseEntity<List<Client>> findAll(){
		List<Client> list = new ArrayList<>();
		list.add(new Client(1L,"João Lucca","3872573876",1000,'2013-09-08T03:00:00.1z',1));
		list.add(new Client )
	}
	

	
}
