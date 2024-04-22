package xenapte.customhud;

import com.mojang.logging.LogUtils;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import xenapte.customhud.hud.CustomCoords;
import xenapte.customhud.hud.CustomTimeFormat;

import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final HudGroup HUD_GROUP = new HudGroup("Custom Text HUD");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Meteor Custom Text HUD");

        // HUD
        Hud.get().register(CustomCoords.INFO);
        Hud.get().register(CustomTimeFormat.INFO);
    }

    @Override
    public String getPackage() {
        return "xenapte.customhud";
    }
}
