package com.persistent.training.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.training.entity.InternshipApplicationEntity;
import com.persistent.training.exception.RecordNotFoundException;
import com.persistent.training.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public List<InternshipApplicationEntity> getAllEmployees()
	{
		List<InternshipApplicationEntity> result = (List<InternshipApplicationEntity>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<InternshipApplicationEntity>();
		}
	}
	
	public InternshipApplicationEntity getEmployeeById(Long id) throws RecordNotFoundException 
	{
		Optional<InternshipApplicationEntity> employee = repository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}
	
	public InternshipApplicationEntity createOrUpdateEmployee(InternshipApplicationEntity entity) 
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<InternshipApplicationEntity> employee = repository.findById(entity.getId());
			
			if(employee.isPresent()) 
			{
				InternshipApplicationEntity newEntity = employee.get();
				
				
				newEntity.setTitle(entity.getTitle());
				newEntity.setDuration(entity.getDuration());
				newEntity.setDescription(entity.getDescription());
				newEntity.setDomain(entity.getDomain());
				

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteEmployeeById(Long id) throws RecordNotFoundException 
	{
		Optional<InternshipApplicationEntity> employee = repository.findById(id);
		
		if(employee.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	} 
}