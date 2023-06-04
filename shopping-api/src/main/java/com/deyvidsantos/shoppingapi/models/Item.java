package com.deyvidsantos.shoppingapi.models;

import com.deyvidsantos.shoppingclient.dto.ItemDTO;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Item {
    
    private String productIdentifier;
    private Float price;

    public static Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProductIdentifier(itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}
