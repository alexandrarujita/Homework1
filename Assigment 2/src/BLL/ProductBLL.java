package BLL;

import java.util.ArrayList;

import DAL.ProductAccess;
import model.Product;

public class ProductBLL {
	
	private ProductAccess prodAcc= new ProductAccess();

	
	public boolean existingProd(Product prod){
		ArrayList<Product> myProd=prodForSale();
		for(Product p:myProd){
			if(p.getId()==prod.getId())
				return true;
		}
		return false;
		
	}
	
	public void setId(Product prod){
		ArrayList<Product> myProd=prodForSale();
		for(Product p:myProd){
			if(p.getName().equals(prod.getName()))
				prod.setId(p.getId());
			
		}
		
	}
	
	public void setPrice(Product prod){
		ArrayList<Product> myProd=prodForSale();
		for(Product p:myProd){
			if(p.getName().equals(prod.getName()))
				prod.setPrice(p.getPrice());
			
		}
	}
	
	public void newProduct(Product prod){
		prodAcc.add(prod);
		
	}
	
	
	public ArrayList<Product> prodForSale(){
		return prodAcc.getProd();	
	}

	public void update(Product p) {
		prodAcc.update(p);
		
	}

	public void delete(Product p) {
		prodAcc.delete(p);
	}

}
