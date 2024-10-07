package com.mresult.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mresult.api.entity.Response_Entity;

public interface RequestRepository extends JpaRepository<Response_Entity, Long>{

}
