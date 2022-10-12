package com.example.demo.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ShipmentTransactions")
public class ShipmentTransactions {
	
	@Id
	private ObjectId id;
	
	private String username;
	private String password;
	private String confirm_password;
		
	private String shipment_Num;
    private String container_num;
    private String description;
    private String route;
    private String goods;
    private String device;  
    private String date;
    private String po_no;
    private String delivery_num;
    private String ndc_num;
    private String batch_id;
    private String serial_num;
	public String getShipment_Num() {
		return shipment_Num;
	}
	public void setShipment_Num(String shipment_Num) {
		this.shipment_Num = shipment_Num;
	}
	public String getContainer_num() {
		return container_num;
	}
	public void setContainer_num(String container_num) {
		this.container_num = container_num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getPo_no() {
		return po_no;
	}
	public void setPo_no(String po_no) {
		this.po_no = po_no;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getDelivery_num() {
		return delivery_num;
	}
	public void setDelivery_num(String delivery_num) {
		this.delivery_num = delivery_num;
	}
	public String getNdc_num() {
		return ndc_num;
	}
	public void setNdc_num(String ndc_num) {
		this.ndc_num = ndc_num;
	}
	public String getSerial_num() {
		return serial_num;
	}
	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

}
