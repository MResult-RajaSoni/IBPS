package com.mresult.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mresult.api.entity.Response_Entity;
import com.mresult.api.repository.RequestRepository;

@Service
public class Request_Service {

	@Autowired
	private RequestRepository repo;
	
	public Response_Entity saveRequest(Response_Entity entity) {
		return repo.save(entity);
	}
	
	public List<Response_Entity> getAllData() {
        return repo.findAll();
    }
	
}


