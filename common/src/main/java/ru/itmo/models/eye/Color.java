package ru.itmo.models.eye;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "eyecolor")
public enum Color implements Serializable {
    RED("RED"),
    BLACK("BLACK"),
    BLUE("BLUE"),
    WHITE("WHITE"),
    BROWN("BROWN");

    private final String title;

    Color(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Color isColor(String title) {
        for (Color color : Color.values()) {
            if (color.getTitle().equalsIgnoreCase(title)) {
                return color;
            }
        }
        return null;
    }


}
