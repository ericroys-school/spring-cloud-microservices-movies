package com.yruhere.moviecatalogservice.model;

import java.util.List;

public class CatalogItems {

    private List<CatalogItem> items;

    public CatalogItems(List<CatalogItem> items) {
        this.items = items;
    }

    public CatalogItems() {
    }

    public List<CatalogItem> getItems() {
        return items;
    }

    public void setItems(List<CatalogItem> items) {
        this.items = items;
    }
}
