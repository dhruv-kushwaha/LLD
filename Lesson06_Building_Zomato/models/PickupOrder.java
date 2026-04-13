package Lesson06_Building_Zomato.models;

public class PickupOrder extends Order {
    private String restrauntAddress;

    public PickupOrder() {
        restrauntAddress = "";
    }

    @Override
    public String getType() {
        return "Pickup";
    }

    public String getRestrauntAddress() {
        return restrauntAddress;
    }

    public void setRestrauntAddress(String restrauntAddress) {
        this.restrauntAddress = restrauntAddress;
    }
}