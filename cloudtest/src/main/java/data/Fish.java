package data;

public class Fish {
	private int id;
	private String breed;
	private float weight;
	private float length;
	private float price;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch (NumberFormatException | NullPointerException e) {
			
		}
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setWeight(String weight) {
		try {
			this.weight = Float.parseFloat(weight);
		}
		catch (NumberFormatException | NullPointerException e) {
			
		}
	}
	public String toString() {
		return this.breed+ " / "+this.weight;
	}
	public boolean isOk() {
		if (this.breed==null || this.weight==0) {
			return false;
		}
		return true;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setPrice(String price) {
		this.price = Float.parseFloat(price);
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public void setLength(String length) {
		this.length = Float.parseFloat(length);
	}
}
