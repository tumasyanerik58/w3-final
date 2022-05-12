package locators;


class ItemsList {

    public static Item[] getItems()
    {
        Item[] items;
        items = new Item[8];
        items[0] = new Item("W3schools full access", "695");
        items[1] = new Item("Web application", "295");
        items[2] = new Item("Front end development", "190");
        items[3] = new Item("Data analytics", "190");
        items[4] = new Item("Learn HTML", "95");
        items[5] = new Item("Learn CSS", "95");
        items[6] = new Item("Learn JavaScript", "95");
        items[7] = new Item("Learn React", "95");
        return items;
    }
}
