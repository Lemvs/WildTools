package com.bgsoftware.wildtools.utils.items;

import com.bgsoftware.wildtools.utils.ServerVersion;

import java.util.EnumMap;

public class OmniToolHelper {

    private static final EnumMap<DestroySpeedCategory, String> TOOL_TYPE_NAMES = new EnumMap<>(DestroySpeedCategory.class);

    static {
        TOOL_TYPE_NAMES.put(DestroySpeedCategory.AXE, "AXE");
        TOOL_TYPE_NAMES.put(DestroySpeedCategory.HOE, "HOE");
        TOOL_TYPE_NAMES.put(DestroySpeedCategory.PICKAXE, "PICKAXE");
        TOOL_TYPE_NAMES.put(DestroySpeedCategory.SHOVEL, ServerVersion.isLegacy() ? "SPADE" : "SHOVEL");
    }

    private OmniToolHelper() {
    }

    public static void init() {
        // Do nothing.
    }

    public static String getToolTypeName(DestroySpeedCategory category) {
        return TOOL_TYPE_NAMES.get(category);
    }

}
