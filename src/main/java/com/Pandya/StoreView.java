package com.Pandya;

import com.Pandya.entity.Store;
import com.Pandya.inventory.InventoryService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StoreView implements Serializable {

    @EJB
    private InventoryService inventoryService;

    public List<Store> getStoreList(){
        return inventoryService.getAllStore();
    }
}
