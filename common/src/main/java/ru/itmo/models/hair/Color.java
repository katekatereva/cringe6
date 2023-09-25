package ru.itmo.models.hair;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "haircolor")
public enum Color implements Serializable {
    GREEN("GREEN"),
    YELLOW("YELLOW"),
    WHITE("WHITE");

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
