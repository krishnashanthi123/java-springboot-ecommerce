package com.ecommerce.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity

public class Tags {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int tagId;

	    private String tagName;

	    @ManyToMany(mappedBy = "tags")
	    private List<Products> products;

		public int getTagId() {
			return tagId;
		}

		public void setTagId(int tagId) {
			this.tagId = tagId;
		}

		public String getTagName() {
			return tagName;
		}

		public void setTagName(String tagName) {
			this.tagName = tagName;
		}

		public List<Products> getProducts() {
			return products;
		}

		public void setProducts(List<Products> products) {
			this.products = products;
		}

	    

}
