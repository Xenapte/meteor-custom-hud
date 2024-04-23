package xenapte.customhud;

import java.util.ArrayList;
import java.util.IllegalFormatException;

import static meteordevelopment.meteorclient.utils.misc.MeteorStarscript.ss;

import meteordevelopment.starscript.value.Value;
import xenapte.customhud.hud.CoordinateFormatter;
import xenapte.customhud.hud.TimeFormatter;
import xenapte.customhud.hud.WeatherDisplay;

public class CustomStarscript {
    public static void onInitialize() {
        ss.set("formatTime", (ss, argCount) -> {
            if (argCount != 1)
                ss.error("formatTime(fmt) requires 1 argument, got %d.", argCount);
            return Value.string(TimeFormatter.format(ss.popString("Argument to formatTime(fmt) needs to be a string.")));
        });

        ss.set("formatCoords", (ss, argCount) -> {
            if (argCount < 1)
                ss.error("formatCoords(fmt) requires 1 argument, got %d.", argCount);
            String fmt = ss.popString("Argument `fmt` to formatCoords() needs to be a string.");
            return Value.string(CoordinateFormatter.format(fmt, false));
        });

        ss.set("formatOppositeCoords", (ss, argCount) -> {
            if (argCount < 1)
                ss.error("formatOppositeCoords(fmt) requires 1 argument, got %d.", argCount);
            String fmt = ss.popString("Argument `fmt` to formatOppositeCoords() needs to be a string.");
            return Value.string(CoordinateFormatter.format(fmt, true));
        });

        ss.set("format", (ss, argCount) -> {
            if (argCount < 1)
                ss.error("format(fmt, ...args) requires at least 1 arguments, got %d.", argCount);
            var args = new ArrayList<Object>();
            for (int i = 1; i < argCount; i ++) {
                Value v = ss.pop(); // can't just add v to args
                Object o = null;
                switch (v.type) {
                    case Boolean: o = v.getBool(); break;
                    case Number: o = v.getNumber(); break;
                    case String: o = v.getString(); break;
                    case Function: o = v.getFunction(); break;
                    case Map: o = v.getMap(); break;
                    default: break;
                }
                args.add(0, o);
            }
            String fmt = ss.popString("Argument `fmt` to format() needs to be a string.");
            try {
                return Value.string(String.format(fmt, args.toArray()));
            }
            catch (IllegalFormatException e) {
                return Value.string(e.toString());
            }
        });

        ss.set("weather", () -> { return Value.string(WeatherDisplay.getWeather()); });
    }
}
