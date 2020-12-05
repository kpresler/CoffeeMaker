package edu.ncsu.csc.CoffeeMaker.services;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.CoffeeMaker.models.DomainObject;

abstract public class Service {
	
	abstract protected JpaRepository<DomainObject, ? extends Object> getRepository();
	
	public void save (DomainObject obj) {
		getRepository().saveAndFlush(obj);
	}
	
	public List<? extends DomainObject> findAll(){
		return getRepository().findAll();
	}
	
	public void saveAll(List <? extends DomainObject> objects) {
		getRepository().saveAll(objects);
		getRepository().flush();
	}
	
	public void delete(DomainObject obj) {
		getRepository().delete(obj);
	}
	
	public void deleteAll() {
		getRepository().deleteAll();
	}
	
	public long count() {
		return getRepository().count();
	}
	
	protected List<? extends DomainObject> findBy(Example<DomainObject> example) {
		return getRepository().findAll(example);
		
	}
	
	
}
