package com.ecommerce.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "seller_info")
public class SellerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sellerinfo_id")
    private int sellerinfoId;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_address")
    private String sellerAddress;

	    
	    @OneToOne
	    @JoinColumn(name = "sellerinfo_id")
	    private SellerInfo sellerInfo;
	    
	    
		public int getSellerinfoId() {
			return sellerinfoId;
		}

		public void setSellerinfoId(int sellerinfoId) {
			this.sellerinfoId = sellerinfoId;
		}

		public String getSellerName() {
			return sellerName;
		}

		public void setSellerName(String sellerName) {
			this.sellerName = sellerName;
		}

		public String getSellerAddress() {
			return sellerAddress;
		}

		public void setSellerAddress(String sellerAddress) {
			this.sellerAddress = sellerAddress;
		}

		
	   
	}


