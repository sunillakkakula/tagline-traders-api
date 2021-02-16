package com.vtt.apps.util;

import com.vtt.apps.model.AvailableInBulk;
import com.vtt.apps.model.AvailableInDomestic;
import com.vtt.apps.model.CartItem;
import com.vtt.apps.model.Product;

public class ProductToCartItemConverter {
	public static CartItem convert(Product product) {
		CartItem cartItem = null;
		/*
		 * String orderType = null; String selectedUom = null; Float unitPrice = null;
		 * Float mrp = null; Float sellingPrice = null;
		 */
		String orderType = null; 
		AvailableInBulk availableInBulk = null;
		AvailableInDomestic availableInDomestic= null;
		if(product!=null)
		{
			cartItem = new CartItem();
			cartItem.setName(product.getName());
			cartItem.setBrand(product.getBrand());
			cartItem.setDescription(product.getDescription());
			cartItem.setImageurl(product.getImageurl());
			cartItem.setIsTaxable(product.getIsTaxable());
			cartItem.setTaxPercent(product.getTaxPercent());
			cartItem.setOrderType(product.getOrderType());
			cartItem.setSelectedUom(product.getSelectedUom());
			orderType = product.getOrderType();
			
			
			if(orderType.equalsIgnoreCase("bulk")) {
				availableInBulk = product.getAvailableInBulk().stream().filter(eachItem->product.getSelectedUom().equalsIgnoreCase(eachItem.getUnitOfMessure())).findFirst().get();
				cartItem.setMrp(availableInBulk.getMrp());
				cartItem.setSellingPrice(availableInBulk.getSellingPrice());
				cartItem.setUnitPrice(availableInBulk.getUnitPrice());
				cartItem.setQty(availableInBulk.getQty());
			}
			else if(orderType.equalsIgnoreCase("domestic")) {
				availableInDomestic = product.getAvailableInDomestic().stream().filter(eachItem->product.getSelectedUom().equalsIgnoreCase(eachItem.getUnitOfMessure())).findFirst().get();
				cartItem.setMrp(availableInDomestic.getMrp());
				cartItem.setSellingPrice(availableInDomestic.getSellingPrice());
				cartItem.setUnitPrice(availableInDomestic.getUnitPrice());
				cartItem.setQty(availableInDomestic.getQty());
			}
		}
		return cartItem;

	}
}
