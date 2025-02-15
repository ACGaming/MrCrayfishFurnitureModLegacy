package com.mrcrayfish.furniture.handler;

import java.io.File;
import com.mrcrayfish.furniture.api.RecipeRegistry;
import com.mrcrayfish.furniture.api.Recipes;
import com.mrcrayfish.furniture.integration.crafttweaker.CraftTweakerIntegration;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler
{
    public static final String CATEGORY_API = "recipe-api";
    public static final String CATEGORY_INTEGRATION_SETTINGS = "integration-settings";
    public static final String CATEGORY_RECIPE_SETTINGS = "recipe-settings";
    public static final String CATEGORY_SETTINGS = "settings";

    public static Configuration config;

    public static boolean allowAllDishwasher = false;
    public static boolean allowAllWashingMachine = false;
    public static boolean api_debug = false;
    public static boolean integrationBiomesOPlenty = true;
    public static boolean mirrorClouds = false;
    public static boolean mirrorEnabled = true;
    public static float mirrorFov = 80F;
    public static int mirrorQuality = 64;
    public static boolean seasonalPresents = true;
    public static String[] trustedUrlDomains = {};
    public static String trustedUrlDomainsListType = "WHITELIST";
    public static int maxFileSize = 2;

    public static String[] items = {};

    public static boolean printer_1 = true, printer_2 = true;
    public static boolean oven_1 = true, oven_2 = true, oven_3 = true, oven_4 = true, oven_5 = true, oven_6 = true, oven_7 = true;
    public static boolean frez_1 = true, frez_2 = true, frez_3 = true, frez_4 = true, frez_5 = true, frez_6 = true;
    public static boolean mine_1 = true, mine_2 = true, mine_3 = true, mine_4 = true, mine_5 = true, mine_6 = true, mine_7 = true, mine_8 = true, mine_9 = true, mine_10 = true;
    public static boolean blen_1 = true, blen_2 = true, blen_3 = true, blen_4 = true;
    public static boolean chop_1 = true;
    public static boolean dish_1 = true, dish_2 = true, dish_3 = true, dish_4 = true, dish_5 = true, dish_6 = true, dish_7 = true, dish_8 = true, dish_9 = true, dish_10 = true;
    public static boolean dish_11 = true, dish_12 = true, dish_13 = true, dish_14 = true, dish_15 = true, dish_16 = true, dish_17 = true, dish_18 = true, dish_19 = true, dish_20 = true;
    public static boolean dish_21 = true, dish_22 = true, dish_23 = true, dish_24 = true, dish_25 = true, dish_26 = true, dish_27 = true, dish_28 = true, dish_29 = true, dish_30 = true;

    public static boolean micr_1 = true, micr_2 = true;

    public static boolean toast_1 = true;

    public static boolean wash_1 = true, wash_2 = true, wash_3 = true, wash_4 = true, wash_5 = true, wash_6 = true, wash_7 = true, wash_8 = true, wash_9 = true, wash_10 = true;
    public static boolean wash_11 = true, wash_12 = true, wash_13 = true, wash_14 = true, wash_15 = true, wash_16 = true, wash_17 = true, wash_18 = true, wash_19 = true, wash_20 = true, wash_21 = true;

    public static boolean grill_1 = true, grill_2 = true, grill_3 = true;

    public static void init(File file)
    {
        if (config == null)
        {
            config = new Configuration(file);
            loadConfig(false);
        }
    }

    public static void loadConfig(boolean shouldChange)
    {
        allowAllDishwasher = config.getBoolean("allow-all-dishwasher", CATEGORY_SETTINGS, false, "Whether to allow all tools to be cleaned inside a dishwasher.");
        allowAllWashingMachine = config.getBoolean("allow-all-washing-machine", CATEGORY_SETTINGS, false, "Whether to allow all armor to be cleaned inside a washing machine.");
        api_debug = config.getBoolean("recipe-api-debug", CATEGORY_SETTINGS, false, "Whether to print out information about RecipeAPI. Recommended 'true' for people trying to add custom recipes.");
        mirrorClouds = config.getBoolean("mirror-clouds", CATEGORY_SETTINGS, false, "Whether the mirror should render clouds.");
        mirrorEnabled = config.getBoolean("mirror-enabled", CATEGORY_SETTINGS, true, "Whether the mirror should render a reflection.");
        mirrorFov = config.getFloat("mirror-fov", CATEGORY_SETTINGS, 80F, 10F, 100F, "The field of view for the mirror.");
        mirrorQuality = config.getInt("mirror-quality", CATEGORY_SETTINGS, 64, 16, 512, "The resolution for the mirror. Higher numbers result in better quality but worse performance.");
        seasonalPresents = config.getBoolean("seasonal-presents", CATEGORY_SETTINGS, true, "Whether opening presents should display a Happy Christmas notification.");
        trustedUrlDomains = config.getStringList("trusted-url-domains", CATEGORY_SETTINGS, trustedUrlDomains, "List of trusted domains to download images for the TV and Photo Frame. For example, the domain of the URL (https://i.imgur.com/Jvh1OQm.jpeg) is i.imgur.com");
        trustedUrlDomainsListType = config.getString("trusted-url-domains-list-type", CATEGORY_SETTINGS, "WHITELIST", "Which list type the list of trusted domains is. Can be either WHITELIST or BLACKLIST.");
        maxFileSize = config.getInt("max-file-size", CATEGORY_SETTINGS, 2, 1, 100, "The maximum file size of images and GIFs in MB the TV and Photo Frame can download.");

        integrationBiomesOPlenty = config.getBoolean("integration-biomesoplenty", CATEGORY_INTEGRATION_SETTINGS, true, "Whether furniture with support for Biomes O' Plenty should be enabled.");

        items = config.getStringList("custom-recipes", CATEGORY_API, items, "Insert custom recipes here");

        config.addCustomCategoryComment(CATEGORY_RECIPE_SETTINGS, "Enable or disable the default recipes");
        config.addCustomCategoryComment(CATEGORY_API, "RecipeAPI Configuration. How to use: https://mrcrayfishs-furniture-mod.fandom.com/wiki/Configuration");

        updateEnabledRecipes();

        if (config.hasChanged() && shouldChange)
        {
            Recipes.clearLocalRecipes();
            Recipes.clearCommRecipes();
            RecipeRegistry.registerDefaultRecipes();
            RecipeRegistry.registerConfigRecipes();
            Recipes.addCommRecipesToLocal();
            if (Loader.isModLoaded("crafttweaker"))
            {
                CraftTweakerIntegration.apply();
            }
            Recipes.updateDataList();
        }
        config.save();
    }

    private static void updateEnabledRecipes()
    {
        printer_1 = config.getBoolean("printer-1", CATEGORY_RECIPE_SETTINGS, printer_1, "Enchanted Book");
        printer_2 = config.getBoolean("printer-2", CATEGORY_RECIPE_SETTINGS, printer_2, "Written Book");
        oven_1 = config.getBoolean("oven-1", CATEGORY_RECIPE_SETTINGS, oven_1, "Beef -> Cooked Beef");
        oven_2 = config.getBoolean("oven-2", CATEGORY_RECIPE_SETTINGS, oven_2, "Porkchop -> Cooked Porkchop");
        oven_3 = config.getBoolean("oven-3", CATEGORY_RECIPE_SETTINGS, oven_3, "Potato -> Cooked Potato");
        oven_4 = config.getBoolean("oven-4", CATEGORY_RECIPE_SETTINGS, oven_4, "Chicken -> Cooked Chicken");
        oven_5 = config.getBoolean("oven-5", CATEGORY_RECIPE_SETTINGS, oven_5, "Raw Fish -> Cooked Fish");
        oven_6 = config.getBoolean("oven-6", CATEGORY_RECIPE_SETTINGS, oven_6, "Raw Salmon -> Cooked Salmon");
        oven_7 = config.getBoolean("oven-7", CATEGORY_RECIPE_SETTINGS, oven_7, "Flesh -> Cooked Flesh");
        frez_1 = config.getBoolean("freezer-1", CATEGORY_RECIPE_SETTINGS, frez_1, "Water Bucket -> Ice");
        frez_2 = config.getBoolean("freezer-2", CATEGORY_RECIPE_SETTINGS, frez_2, "Ice -> Packet Ice");
        frez_3 = config.getBoolean("freezer-3", CATEGORY_RECIPE_SETTINGS, frez_3, "Lava Bucket -> Obsidian");
        frez_4 = config.getBoolean("freezer-4", CATEGORY_RECIPE_SETTINGS, frez_4, "Slime Ball -> Snow Ball");
        frez_5 = config.getBoolean("freezer-5", CATEGORY_RECIPE_SETTINGS, frez_5, "Poinsonous Potato -> Potato");
        frez_6 = config.getBoolean("freezer-6", CATEGORY_RECIPE_SETTINGS, frez_6, "Rotten Flesh -> Flesh");
        mine_1 = config.getBoolean("minebay-1", CATEGORY_RECIPE_SETTINGS, mine_1, "16 Hardened Clay for 1 Emerald");
        mine_2 = config.getBoolean("minebay-2", CATEGORY_RECIPE_SETTINGS, mine_2, "1 Skeleton Skull for 8 Emeralds");
        mine_3 = config.getBoolean("minebay-3", CATEGORY_RECIPE_SETTINGS, mine_3, "1 Saddle for 4 Emeralds");
        mine_4 = config.getBoolean("minebay-4", CATEGORY_RECIPE_SETTINGS, mine_4, "1 Horse Spawn Egg for 8 Emeralds");
        mine_5 = config.getBoolean("minebay-5", CATEGORY_RECIPE_SETTINGS, mine_5, "1 Diamond Horse Armour for 8 Diamonds");
        mine_6 = config.getBoolean("minebay-6", CATEGORY_RECIPE_SETTINGS, mine_6, "1 Experience Bottle for 1 Iron Ingot");
        mine_7 = config.getBoolean("minebay-7", CATEGORY_RECIPE_SETTINGS, mine_7, "4 Christmas Firework for 1 Iron Ingot");
        mine_8 = config.getBoolean("minebay-8", CATEGORY_RECIPE_SETTINGS, mine_8, "1 Silk Touch Book for 8 Emeralds");
        mine_9 = config.getBoolean("minebay-9", CATEGORY_RECIPE_SETTINGS, mine_9, "2 Night Vision Potion for 1 Emerald");
        mine_10 = config.getBoolean("minebay-10", CATEGORY_RECIPE_SETTINGS, mine_10, "1 Recipe Book for 1 Emerald");
        blen_1 = config.getBoolean("blender-1", CATEGORY_RECIPE_SETTINGS, blen_1, "Fruit Crush");
        blen_2 = config.getBoolean("blender-2", CATEGORY_RECIPE_SETTINGS, blen_2, "Veggie Juice");
        blen_3 = config.getBoolean("blender-3", CATEGORY_RECIPE_SETTINGS, blen_3, "Fishy Blend");
        blen_4 = config.getBoolean("blender-4", CATEGORY_RECIPE_SETTINGS, blen_4, "Energy Drink");
        chop_1 = config.getBoolean("chopping-board-1", CATEGORY_RECIPE_SETTINGS, chop_1, "Bread -> 6 Bread Slices");
        dish_1 = config.getBoolean("dishwasher-1", CATEGORY_RECIPE_SETTINGS, dish_1, "Bow");
        dish_2 = config.getBoolean("dishwasher-2", CATEGORY_RECIPE_SETTINGS, dish_2, "Wooden Pickaxe");
        dish_3 = config.getBoolean("dishwasher-3", CATEGORY_RECIPE_SETTINGS, dish_3, "Wooden Axe");
        dish_4 = config.getBoolean("dishwasher-4", CATEGORY_RECIPE_SETTINGS, dish_4, "Wooden Shovel");
        dish_5 = config.getBoolean("dishwasher-5", CATEGORY_RECIPE_SETTINGS, dish_5, "Wooden Hoe");
        dish_6 = config.getBoolean("dishwasher-6", CATEGORY_RECIPE_SETTINGS, dish_6, "Wooden Sword");
        dish_7 = config.getBoolean("dishwasher-7", CATEGORY_RECIPE_SETTINGS, dish_7, "Stone Pickaxe");
        dish_8 = config.getBoolean("dishwasher-8", CATEGORY_RECIPE_SETTINGS, dish_8, "Stone Axe");
        dish_9 = config.getBoolean("dishwasher-9", CATEGORY_RECIPE_SETTINGS, dish_9, "Stone Shovel");
        dish_10 = config.getBoolean("dishwasher-10", CATEGORY_RECIPE_SETTINGS, dish_10, "Stone Hoe");
        dish_11 = config.getBoolean("dishwasher-11", CATEGORY_RECIPE_SETTINGS, dish_11, "Stone Sword");
        dish_12 = config.getBoolean("dishwasher-12", CATEGORY_RECIPE_SETTINGS, dish_12, "Iron Pickaxe");
        dish_13 = config.getBoolean("dishwasher-13", CATEGORY_RECIPE_SETTINGS, dish_13, "Iron Axe");
        dish_14 = config.getBoolean("dishwasher-14", CATEGORY_RECIPE_SETTINGS, dish_14, "Iron Shovel");
        dish_15 = config.getBoolean("dishwasher-15", CATEGORY_RECIPE_SETTINGS, dish_15, "Iron Hoe");
        dish_16 = config.getBoolean("dishwasher-16", CATEGORY_RECIPE_SETTINGS, dish_16, "Iron Sword");
        dish_17 = config.getBoolean("dishwasher-17", CATEGORY_RECIPE_SETTINGS, dish_17, "Golden Pickaxe");
        dish_18 = config.getBoolean("dishwasher-18", CATEGORY_RECIPE_SETTINGS, dish_18, "Golden Axe");
        dish_19 = config.getBoolean("dishwasher-19", CATEGORY_RECIPE_SETTINGS, dish_19, "Golden Shovel");
        dish_20 = config.getBoolean("dishwasher-20", CATEGORY_RECIPE_SETTINGS, dish_20, "Golden Hoe");
        dish_21 = config.getBoolean("dishwasher-21", CATEGORY_RECIPE_SETTINGS, dish_21, "Golden Sword");
        dish_22 = config.getBoolean("dishwasher-22", CATEGORY_RECIPE_SETTINGS, dish_22, "Diamond Pickaxe");
        dish_23 = config.getBoolean("dishwasher-23", CATEGORY_RECIPE_SETTINGS, dish_23, "Diamond Axe");
        dish_24 = config.getBoolean("dishwasher-24", CATEGORY_RECIPE_SETTINGS, dish_24, "Diamond Shovel");
        dish_25 = config.getBoolean("dishwasher-25", CATEGORY_RECIPE_SETTINGS, dish_25, "Diamond Hoe");
        dish_26 = config.getBoolean("dishwasher-26", CATEGORY_RECIPE_SETTINGS, dish_26, "Diamond Sword");
        dish_27 = config.getBoolean("dishwasher-27", CATEGORY_RECIPE_SETTINGS, dish_27, "Fishing Rod");
        dish_28 = config.getBoolean("dishwasher-28", CATEGORY_RECIPE_SETTINGS, dish_28, "Flint and Steel");
        dish_29 = config.getBoolean("dishwasher-29", CATEGORY_RECIPE_SETTINGS, dish_29, "Shears");
        dish_30 = config.getBoolean("dishwasher-30", CATEGORY_RECIPE_SETTINGS, dish_30, "Shield");

        micr_1 = config.getBoolean("microwave-1", CATEGORY_RECIPE_SETTINGS, micr_1, "Beef -> Cooked Beef");
        micr_2 = config.getBoolean("microwave-2", CATEGORY_RECIPE_SETTINGS, micr_2, "Potato -> Baked Potato");

        toast_1 = config.getBoolean("toast-2", CATEGORY_RECIPE_SETTINGS, toast_1, "Bread Slice -> Toast");

        wash_1 = config.getBoolean("washing-machine-1", CATEGORY_RECIPE_SETTINGS, wash_1, "Leather Helmet");
        wash_2 = config.getBoolean("washing-machine-2", CATEGORY_RECIPE_SETTINGS, wash_2, "Leather Chestplate");
        wash_3 = config.getBoolean("washing-machine-3", CATEGORY_RECIPE_SETTINGS, wash_3, "Leather Leggings");
        wash_4 = config.getBoolean("washing-machine-4", CATEGORY_RECIPE_SETTINGS, wash_4, "Leather Boots");
        wash_5 = config.getBoolean("washing-machine-5", CATEGORY_RECIPE_SETTINGS, wash_5, "Chainmail Helmet");
        wash_6 = config.getBoolean("washing-machine-6", CATEGORY_RECIPE_SETTINGS, wash_6, "Chainmail Chestplate");
        wash_7 = config.getBoolean("washing-machine-7", CATEGORY_RECIPE_SETTINGS, wash_7, "Chainmail Leggings");
        wash_8 = config.getBoolean("washing-machine-8", CATEGORY_RECIPE_SETTINGS, wash_8, "Chainmail Boots");
        wash_9 = config.getBoolean("washing-machine-9", CATEGORY_RECIPE_SETTINGS, wash_9, "Iron Helmet");
        wash_10 = config.getBoolean("washing-machine-10", CATEGORY_RECIPE_SETTINGS, wash_10, "Iron Chestplate");
        wash_11 = config.getBoolean("washing-machine-11", CATEGORY_RECIPE_SETTINGS, wash_11, "Iron Leggings");
        wash_12 = config.getBoolean("washing-machine-12", CATEGORY_RECIPE_SETTINGS, wash_12, "Iron Boots");
        wash_13 = config.getBoolean("washing-machine-13", CATEGORY_RECIPE_SETTINGS, wash_13, "Golden Helmet");
        wash_14 = config.getBoolean("washing-machine-14", CATEGORY_RECIPE_SETTINGS, wash_14, "Golden Chestplate");
        wash_15 = config.getBoolean("washing-machine-15", CATEGORY_RECIPE_SETTINGS, wash_15, "Golden Leggings");
        wash_16 = config.getBoolean("washing-machine-16", CATEGORY_RECIPE_SETTINGS, wash_16, "Golden Boots");
        wash_17 = config.getBoolean("washing-machine-17", CATEGORY_RECIPE_SETTINGS, wash_17, "Diamond Helmet");
        wash_18 = config.getBoolean("washing-machine-18", CATEGORY_RECIPE_SETTINGS, wash_18, "Diamond Chestplate");
        wash_19 = config.getBoolean("washing-machine-19", CATEGORY_RECIPE_SETTINGS, wash_19, "Diamond Leggings");
        wash_20 = config.getBoolean("washing-machine-20", CATEGORY_RECIPE_SETTINGS, wash_20, "Diamond Boots");
        wash_21 = config.getBoolean("washing-machine-21", CATEGORY_RECIPE_SETTINGS, wash_21, "Elytra");

        grill_1 = config.getBoolean("grill-1", CATEGORY_RECIPE_SETTINGS, grill_1, "Beef -> Cooked Beef");
        grill_2 = config.getBoolean("grill-2", CATEGORY_RECIPE_SETTINGS, grill_2, "Sausage -> Cooked Sausage");
        grill_3 = config.getBoolean("grill-3", CATEGORY_RECIPE_SETTINGS, grill_3, "Raw Kebab -> Cooked Kebab");
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
    {
        if (eventArgs.getModID().equals("cfm"))
        {
            loadConfig(true);
        }
    }
}
