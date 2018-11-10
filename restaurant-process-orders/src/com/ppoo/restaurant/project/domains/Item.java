package com.ppoo.restaurant.project.domains;

public abstract class Item {

    private Long id;
    private String name;
    private Double price;
    private Integer cantity;
    private Integer vat;

    public Item(Long id, String name, Double price, Integer cantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cantity = cantity;
    }

    public Item() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCantity() {
        return cantity;
    }

    public void setCantity(Integer cantity) {
        this.cantity = cantity;
    }

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }
}
