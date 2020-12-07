package edu.ncsu.csc.CoffeeMaker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.CoffeeMaker.models.Inventory;
import edu.ncsu.csc.CoffeeMaker.models.Recipe;
import edu.ncsu.csc.CoffeeMaker.services.InventoryService;
import edu.ncsu.csc.CoffeeMaker.services.RecipeService;


/**
 * This is the single controller in the CoffeeMaker application that handles
 * REST API endpoints In a larger application, we would want multiple REST API
 * controllers, one per model.
 *
 * Spring will automatically convert all of the ResponseEntity and List results
 * to JSON
 *
 * @author Kai Presler-Marshall
 *
 */
@SuppressWarnings ( { "unchecked", "rawtypes" } )
@RestController
public class APICoffeeController extends APIController {

	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private RecipeService recipeService;
	
    /**
     * REST API method to make coffee by completing a POST request with the ID
     * of the recipe as the path variable and the amount that has been paid as
     * the body of the response
     *
     * @param name
     *            recipe name
     * @param amtPaid
     *            amount paid
     * @return The change the customer is due if successful
     */
    @PostMapping ( BASE_PATH + "/makecoffee/{name}" )
    public ResponseEntity makeCoffee ( @PathVariable ( "name" ) final String name, @RequestBody final int amtPaid ) {
        final Recipe recipe = recipeService.findByName(name);
        if ( recipe == null ) {
            return new ResponseEntity( errorResponse( "No recipe selected" ), HttpStatus.NOT_FOUND );
        }

        final int change = makeCoffee( recipe, amtPaid );
        if ( change == amtPaid ) {
            if ( amtPaid < recipe.getPrice() ) {
                return new ResponseEntity( errorResponse( "Not enough money paid" ), HttpStatus.CONFLICT );
            }
            else {
                return new ResponseEntity( errorResponse( "Not enough inventory" ), HttpStatus.CONFLICT );
            }
        }
        return new ResponseEntity<String>( successResponse( String.valueOf( change ) ), HttpStatus.OK );

    }

    /**
     * Helper method to make coffee
     *
     * @param toPurchase
     *            recipe that we want to make
     * @param amtPaid
     *            money that the user has given the machine
     * @return change if there was enough money to make the coffee, throws
     *         exceptions if not
     */
    private int makeCoffee ( final Recipe toPurchase, final int amtPaid ) {
        int change = amtPaid;
        final Inventory inventory = inventoryService.getInventory();

        if ( toPurchase == null ) {
            throw new IllegalArgumentException( "Recipe not found" );
        }
        else if ( toPurchase.getPrice() <= amtPaid ) {
            if ( inventory.useIngredients( toPurchase ) ) {
                inventoryService.save(inventory);
                change = amtPaid - toPurchase.getPrice();
                return change;
            }
            else {
                // not enough inventory
                return change;
            }
        }
        // not enough money
        return change;
    }
}
