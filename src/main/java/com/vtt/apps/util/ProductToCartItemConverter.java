package com.vtt.apps.util;

import com.vtt.apps.model.AvailableInBulk;
import com.vtt.apps.model.AvailableInDomestic;
import com.vtt.apps.model.CartItem;
import com.vtt.apps.model.Product;

public class ProductToCartItemConverter {
	public static CartItem convert(Product product,String qty,String orderType,String selectedUOM) {
		CartItem cartItem = null;
		/*
		 * String orderType = null; String selectedUom = null; Float unitPrice = null;
		 * Float mrp = null; Float sellingPrice = null;
		 */
		AvailableInBulk availableInBulk = null;
		AvailableInDomestic availableInDomestic= null;
		if(product!=null)
		{
			cartItem = new CartItem();
			cartItem.setName(product.getName());
			cartItem.setBrand(product.getBrand());
			cartItem.setDescription(product.getDescription());
			cartItem.setImageUrl(product.getImageUrl());
			cartItem.setIsTaxable(product.getIsTaxable());
			cartItem.setTaxPercent(product.getTaxPercent());
			cartItem.setSelectedUom(selectedUOM);
			cartItem.setOrderType(orderType);
			cartItem.setQty(Integer.parseInt(qty));
			
			if(orderType.equalsIgnoreCase("bulk")) {
				availableInBulk = product.getAvailableInBulk().stream().filter(eachItem->eachItem.getUnitOfMessure().equalsIgnoreCase(selectedUOM)).findFirst().get();
				cartItem.setMrp(availableInBulk.getMrp());
				cartItem.setSellingPrice(availableInBulk.getSellingPrice());
				cartItem.setUnitPrice(availableInBulk.getUnitPrice());
			}
			else if(orderType.equalsIgnoreCase("domestic")) {
				availableInDomestic = product.getAvailableInDomestic().stream().filter(eachItem->eachItem.getUnitOfMessure().equalsIgnoreCase(selectedUOM)).findFirst().get();
				cartItem.setMrp(availableInDomestic.getMrp());
				cartItem.setSellingPrice(availableInDomestic.getSellingPrice());
				cartItem.setUnitPrice(availableInDomestic.getUnitPrice());
			}
		}
		return cartItem;

	}
}
