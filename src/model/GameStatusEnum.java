package model;

public enum GameStatusEnum {

    NON_STARTED("not started"),
    INCOMPLETE("incomplete"),
    COMPLETE("complete");

    private String label;

    GameStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
