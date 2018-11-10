package com.ppoo.restaurant.project.domains.absstract;

public abstract class MenuItem {

    private Long itemId;
    private String name;
    private Double price;
    private Integer vat;

    public MenuItem(Long id, String name, Double price) {
        this.itemId = id;
        this.name = name;
        this.price = price;
    }

    public MenuItem() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }
}
