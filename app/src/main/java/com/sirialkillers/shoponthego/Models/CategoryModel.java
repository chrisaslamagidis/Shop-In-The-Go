package com.sirialkillers.shoponthego.Models;

import com.sirialkillers.shoponthego.Interfaces.ICategory;

/**
 * @author Ioakeim James Theologou
 * @version 16/11/2017
 * TODO: Create a test class for this model.
 *
 */
public class CategoryModel implements ICategory{
    private String categoryId;
    private String categoryName;
    private String description;

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
