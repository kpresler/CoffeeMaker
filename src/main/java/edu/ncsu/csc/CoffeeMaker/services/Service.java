package edu.ncsu.csc.CoffeeMaker.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.CoffeeMaker.models.DomainObject;

abstract public class Service {
	
	abstract protected JpaRepository<DomainObject, ? extends Object> getRepository();
	
	public void save (DomainObject obj) {
		getRepository().save(obj);
	}
	
	public List<? super DomainObject> findAll(){
		return getRepository().findAll();
	}
	
	public void saveAll(List <? extends DomainObject> objects) {
		getRepository().saveAll(objects);
	}
	
	public void delete(DomainObject obj) {
		getRepository().delete(obj);
	}
	
	public void deleteAll() {
		getRepository().deleteAll();
	}
}
