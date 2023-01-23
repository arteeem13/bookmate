package com.bookmate.pageObjects;

public enum CategoriesInTopMenu {
    SEARCH("Поиск"),
    BOOKS("Книги"),
    AUDIOBOOKS("Аудиокниги"),
    COMICBOOKS("Комиксы");

    private String desc;

    CategoriesInTopMenu(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
