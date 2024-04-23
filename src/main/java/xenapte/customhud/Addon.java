package xenapte.customhud;

import com.mojang.logging.LogUtils;

import static meteordevelopment.meteorclient.utils.misc.MeteorStarscript.ss;

import java.util.ArrayList;
import java.util.IllegalFormatException;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.starscript.value.Value;
import xenapte.customhud.hud.CoordinateFormatter;
import xenapte.customhud.hud.TimeFormatter;

import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final HudGroup HUD_GROUP = new HudGroup("Custom Text HUD");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Meteor Custom Text HUD");

        // HUD
        Hud.get().register(CoordinateFormatter.INFO);
        Hud.get().register(TimeFormatter.INFO);

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
    }

    @Override
    public String getPackage() {
        return "xenapte.customhud";
    }
}
