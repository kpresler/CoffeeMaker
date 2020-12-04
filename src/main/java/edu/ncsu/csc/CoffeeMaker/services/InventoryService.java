package edu.ncsu.csc.CoffeeMaker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.CoffeeMaker.models.Inventory;
import edu.ncsu.csc.CoffeeMaker.repositories.InventoryRepository;

@Component
@Transactional
public class InventoryService extends Service {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	protected JpaRepository getRepository() {
		return inventoryRepository;
	}
	
	public Inventory getInventory() {
        final List<Inventory> inventoryList = (List<Inventory>) findAll();
        if ( inventoryList != null && inventoryList.size() == 1 ) {
            return inventoryList.get( 0 );
        }
        else {
            // initialize the inventory with 0 of everything
            final Inventory i = new Inventory( 0, 0, 0, 0 );
            save(i);
            return i;
        }
	}

}
