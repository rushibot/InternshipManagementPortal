package com.persistent.training.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.persistent.training.entity.InternshipApplicationEntity;



@Repository
public interface EmployeeRepository 
			extends CrudRepository<InternshipApplicationEntity, Long> {

}
