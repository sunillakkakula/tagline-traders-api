/*
 * package com.vtt.apps.util;
 * 
 * import java.util.ArrayList; import java.util.Iterator; import java.util.List;
 * 
 * import com.vtt.apps.model.Administrator; import com.vtt.apps.model.Category;
 * import com.vtt.apps.model.Farmer; import com.vtt.apps.model.Supplier; import
 * com.vtt.apps.pojo.AdministratorPojo; import com.vtt.apps.pojo.FarmerPojo;
 * import com.vtt.apps.pojo.SupplierPojo;
 * 
 * public class ModelToPojoConverter {
 * 
 * public static List<com.vtt.apps.pojo.Category>
 * convertModelToPojo(List<Category> categoryList){
 * List<com.vtt.apps.pojo.Category> categoryPojoList =new ArrayList<>();
 * com.vtt.apps.pojo.Category categoryPojo = null; for (Iterator iterator =
 * categoryList.iterator(); iterator.hasNext();) { Category category =
 * (Category) iterator.next(); categoryPojo = new
 * com.vtt.apps.pojo.Category(category.getId() , category.getName(),
 * category.getImage(), category.getItems().size());
 * categoryPojoList.add(categoryPojo); } return categoryPojoList; }
 * 
 * public static AdministratorPojo convertAdminModelToPojo(Administrator admin){
 * AdministratorPojo pojo = null; if(admin!=null){ pojo= new
 * AdministratorPojo(admin); } return pojo; } public static SupplierPojo
 * convertSupplierModelToPojo(Supplier supplier){ SupplierPojo pojo = null;
 * if(supplier!=null){ pojo= new SupplierPojo(supplier); } return pojo; } public
 * static FarmerPojo convertFarmerModelToPojo(Farmer farmer){ FarmerPojo pojo =
 * null; if(farmer!=null){ pojo= new FarmerPojo(farmer); } return pojo; } }
 */