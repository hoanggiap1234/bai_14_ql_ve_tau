package enums;

public enum TypePerSonBuy {
    RETAILS( "Mua lẻ"), COLLECTIVE_BUY("Mua tập thể"), ONLINE("Mua online" );


    private String type;

    TypePerSonBuy(String type) {

        this.type = type;
    }

    public String getType() {
        return type;
    }
}
