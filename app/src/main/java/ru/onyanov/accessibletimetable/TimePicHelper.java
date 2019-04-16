package ru.onyanov.accessibletimetable;

import java.util.HashMap;

public class TimePicHelper {

    HashMap<Integer, String> library = new HashMap<>();

    public TimePicHelper() {
        library.put(1, "klok 1u.png");
        library.put(2, "klok 2u.png");
        library.put(3, "klok 3u.png");
        library.put(4, "klok 4u.png");
        library.put(5, "klok 5u.png");
        library.put(6, "klok 6u.png");
        library.put(7, "klok 7u.png");
        library.put(8, "klok 8u.png");
        library.put(9, "klok 9u.png");
        library.put(10, "klok 10u.png");
        library.put(11, "klok 11u.png");
        library.put(12, "klok 12u.png");
    }

    public String getTimeIcon(String time) {
        String[] parts = time.split(":");
        int hour;
        try {
            hour = Integer.parseInt(parts[0]);
        }
        catch (NumberFormatException e)
        {
            hour = 12;
        }
        if (hour > 12) hour -= 12;

        return library.get(hour);
    }
}
