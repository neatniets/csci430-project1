public class Supply {
	private String supplier_id;
	private String product_id;
	private double price;
	
	public Supply(String supplier_id, String product_id, double price){
		this.supplier_id = supplier_id;
		this.product_id = product_id;
		this.price = price;
	}
	
	public String getSupplierID(){
		return supplier_id;
	}
	
	public String getProductID(){
		return product_id;
	}
	
	public double getPrice(){
		return price;
	}
}
