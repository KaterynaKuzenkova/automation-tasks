package com.epam.hw.lesson2;

public enum ButtonsListForJdbc {
    STR("started"),
    END("ended"),
    CONT("in progress");

    private String buttonState;

    ButtonsListForJdbc(String buttonState) {
        this.buttonState = buttonState;
    }

    public String getButtonsDesc() {
        return buttonState;
    }

}
