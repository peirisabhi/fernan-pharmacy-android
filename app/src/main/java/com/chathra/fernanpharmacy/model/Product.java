package com.chathra.fernanpharmacy.model;


import java.io.Serializable;
import java.util.Date;


public class Product implements Serializable {

    private Long id;

    private String name;

    private Integer qty;

    private Double discount;

    private String description;

    private Double buyingPrice;

    private Double sellingPrice;

    private Date mfd;

    private Date exp;

    private Date createdDate;

    private String seller;

    private String category;

    private Integer status;

    private String brand;

    private String img;


    public Product(Long id, String name, Integer qty, Double discount, String description, Double buyingPrice, Double sellingPrice, Date mfd, Date exp, Date createdDate, String seller, String category, Integer status, String brand, String img) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.discount = discount;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.mfd = mfd;
        this.exp = exp;
        this.createdDate = createdDate;
        this.seller = seller;
        this.category = category;
        this.status = status;
        this.brand = brand;
        this.img = img;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Date getMfd() {
        return mfd;
    }

    public void setMfd(Date mfd) {
        this.mfd = mfd;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
