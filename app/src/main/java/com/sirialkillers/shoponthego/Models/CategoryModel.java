package com.sirialkillers.shoponthego.Models;

import com.sirialkillers.shoponthego.Interfaces.ICategory;

/**
 * @author Ioakeim James Theologou
 * @version 16/11/2017
 *
 */
public class CategoryModel implements ICategory{
    private String categoryId;
    private String categoryName;
    private String description;

    public CategoryModel(String categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public CategoryModel(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }


    public CategoryModel(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String getCategoryId() {

        return categoryId;
    }

    @Override
    public String getCategoryName() {

        return categoryName;
    }

    @Override
    public String getCategoryDescription() {

        return description;
    }

    public void setCategoryId(String categoryId) {

        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {

        this.categoryName = categoryName;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
