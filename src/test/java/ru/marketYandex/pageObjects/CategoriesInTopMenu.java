package ru.marketYandex.pageObjects;

public enum CategoriesInTopMenu {
    THEMATIC_ENTRYPOINT("Кибернедели"),
    EXPRESS("Экспресс"),
    PHARMACY("Аптека"),
    ALISA("Алиса"),
    IKEA("ИКЕА"),
//    MARKET_15("Маркет 15"),
//    STORE("Универмаг"),
//    PRODUCTS("Продукты"),
//    HOUSE("Дом"),
//    CLOTHES("Одежда"),
//    CHILDREN("Детям"),
//    BEAUTY("Красота"),
//    ELECTRONICS("Электроника"),
//    PLUS_18("18+"),
//    APPLIANCES("Бытовая техника"),
//    COUNTRY_HOUSE("Дача"),
//    FLOWERS("Цветы"),
//    ZOO("Зоо"),
//    DECORATIONS("Украшения"),
//    AUTO("Авто"),
//    FURNITURE("Мебель"),
//    HOBIE("Хобби"),
//    BOOKS("Книги"),
    SCHOOL("Школа");

    private String desc;

    CategoriesInTopMenu(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
