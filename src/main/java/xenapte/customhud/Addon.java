package xenapte.customhud;

import com.mojang.logging.LogUtils;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import xenapte.customhud.hud.CoordinateFormatter;
import xenapte.customhud.hud.LastDeathLocation;
import xenapte.customhud.hud.TimeFormatter;
import xenapte.customhud.hud.WeatherDisplay;

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
        Hud.get().register(WeatherDisplay.INFO);
        Hud.get().register(LastDeathLocation.INFO);

        CustomStarscript.onInitialize();
    }

    @Override
    public String getPackage() {
        return "xenapte.customhud";
    }
}
