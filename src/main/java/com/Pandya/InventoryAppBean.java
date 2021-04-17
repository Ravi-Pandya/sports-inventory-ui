package com.Pandya;

import com.Pandya.entity.Store;
import com.Pandya.interceptor.Logged;
import com.Pandya.entity.Inventory;
import com.Pandya.inventory.InventoryService;
import lombok.Builder;
import lombok.Data;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@SessionScoped
@Named
@Data
public class InventoryAppBean implements Serializable {


    private String name;
    private String sport;
    private Integer quantity;
    private Double pricePerUnit;

    private String location;

    @Logged
    public void addStores(){
        inventoryService.addStore(name, new Store(name,location));
    }

    @EJB
    private InventoryService inventoryService;

    public List<Inventory> getInventoryList(){
        return inventoryService.getInventoryList();
    }

    @Logged
    public String addInventory(){

        Inventory inventory = new Inventory(name,sport,quantity,pricePerUnit);
        Optional<Inventory> inventoryExists = inventoryService.getInventoryList().stream().filter(i ->
                i.getName().equals(name) && i.getSport().equals(sport)).findFirst();
        if (inventoryExists.isPresent()) {
            inventoryService.removeFromList(inventory);
        } else {
            inventoryService.addToList(inventory);
        }
        clearFields();
        return "inventoryList";
    }

    private void clearFields(){
        setName("");
        setSport("");
        setQuantity(null);
        setPricePerUnit(null);
    }

}
