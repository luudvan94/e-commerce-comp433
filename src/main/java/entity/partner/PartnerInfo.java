package entity.partner;

import java.util.Date;  

import javax.persistence.*;


@Entity
@Table(name = "PARTNER_INFO")
public class PartnerInfo {
	@Id
	@Column(name = "partnerInfoID", unique = true, nullable = false)
	private String id;
	
	@Column(name = "partnerID")
	private String partnerID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="date_added")
	private String date_added;
	
	public PartnerInfo() {}
	
	public static String generateID() {
		return "PI" + new Date().getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

}
