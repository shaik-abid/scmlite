package com.example.demo.Entities;

import javax.validation.constraints.Pattern;

//@Entity
public class Shipment {
	
	@Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
	private String shipment_Number;
	@Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String container_no;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
    private String desc;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
    private String route;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
    private String goods;
	@Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String device;  
	@Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String date;
    @Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String po_no;
    @Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String delivery_no;
    @Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String ndc_no;
    @Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String batch_id;
    @Pattern(message="Invalid Input", regexp = "|.[0-9  _.,-]+")
    private String serial_no;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getContainer_no() {
        return container_no;
    }

    public void setContainer_no(String container_no) {
        this.container_no = container_no;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getDelivery_no() {
        return delivery_no;
    }

    public void setDelivery_no(String delivery_no) {
        this.delivery_no = delivery_no;
    }

    public String getNdc_no() {
        return ndc_no;
    }

    public void setNdc_no(String ndc_no) {
        this.ndc_no = ndc_no;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

	public String getShipment_Number() {
		return shipment_Number;
	}

	public void setShipment_Number(String shipment_Number) {
		this.shipment_Number = shipment_Number;
	}
}
