package com.bgsoftware.wildtools.utils.items;

import com.bgsoftware.wildtools.utils.Materials;
import com.bgsoftware.wildtools.utils.ServerVersion;
import org.bukkit.Material;

import java.util.EnumMap;

public class OmniToolHelper {

    private static final EnumMap<Material, EnumMap<DestroySpeedCategory, Material>> TOOL_TYPE_CACHE = new EnumMap<>(Material.class);

    static {
        String shovelSuffix = ServerVersion.isLegacy() ? "SPADE" : "SHOVEL";

        for (Material material : Materials.getTools()) {
            String toolName = material.name();
            int lastUnderscoreIndex = toolName.lastIndexOf('_');

            if (lastUnderscoreIndex != -1) {
                String prefix = toolName.substring(0, lastUnderscoreIndex + 1);

                EnumMap<DestroySpeedCategory, Material> categoryMap = new EnumMap<>(DestroySpeedCategory.class);

                categoryMap.put(DestroySpeedCategory.AXE, getSafeMaterial(prefix + "AXE", material));
                categoryMap.put(DestroySpeedCategory.HOE, getSafeMaterial(prefix + "HOE", material));
                categoryMap.put(DestroySpeedCategory.SHOVEL, getSafeMaterial(prefix + shovelSuffix, material));
                categoryMap.put(DestroySpeedCategory.PICKAXE, getSafeMaterial(prefix + "PICKAXE", material));

                TOOL_TYPE_CACHE.put(material, categoryMap);
            }
        }
    }

    private OmniToolHelper() {
    }

    public static void init() {
        // Do nothing.
    }

    public static boolean isAlreadyCorrectToolType(Material toolType, DestroySpeedCategory destroySpeedCategory) {
        switch (destroySpeedCategory) {
            case AXE:
                return Materials.isAxe(toolType);
            case HOE:
                return Materials.isHoe(toolType);
            case SHOVEL:
                return Materials.isShovel(toolType);
            case PICKAXE:
                return Materials.isPickaxe(toolType);
            default:
                return false;
        }
    }

    public static Material getNewToolType(Material toolType, DestroySpeedCategory category) {
        EnumMap<DestroySpeedCategory, Material> categoryMap = TOOL_TYPE_CACHE.get(toolType);

        return categoryMap != null ? categoryMap.get(category) : toolType;
    }

    private static Material getSafeMaterial(String name, Material fallback) {
        try {
            return Material.valueOf(name);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

}
