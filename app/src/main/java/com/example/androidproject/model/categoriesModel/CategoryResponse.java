package com.example.androidproject.model.categoriesModel;

import java.util.List;

public class CategoryResponse {
    public List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
