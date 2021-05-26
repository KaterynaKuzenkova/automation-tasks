package com.epam.hw.lesson1;

public enum ButtonsList {
    BUTTON1("Button1"),
    BUTTON2("Button2"),
    BUTTON3("Button3"),
    BUTTON4("Button4"),
    BUTTON5("Button5"),
    BUTTON6("Button6"),
    BUTTON7("Button7"),
    BUTTON8("Button8"),
    BUTTON9("Button9"),
    BUTTON10("Button10");

    private String buttonDesc;

    ButtonsList(String buttonDescription) {
        this.buttonDesc = buttonDescription;
    }

    public String getButtonsDesc() {
        return buttonDesc;
    }
}
