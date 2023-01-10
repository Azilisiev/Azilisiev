package items;

public class Item {
	private String name;
	private ItemClass itemClass;
	private int amount;
	
	
	
	public Item(String name, ItemClass itemClass, int amount) {
		super();
		this.name = name;
		this.itemClass = itemClass;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ItemClass getItemClass() {
		return itemClass;
	}
	public void setItemClass(ItemClass itemClass) {
		this.itemClass = itemClass;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void addAmount(int amount) {
		this.amount = this.amount + amount;
	}
	
	
	
	

}
