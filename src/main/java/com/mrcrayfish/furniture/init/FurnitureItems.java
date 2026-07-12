package com.mrcrayfish.furniture.init;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.items.*;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

public class FurnitureItems
{
    /**
     * Initial Furniture
     */
    public static final Item FLESH, COOKED_FLESH, COOL_PACK;

    /**
     * Garden Update
     */
    public static final Item HAMMER;
    public static final Item ENVELOPE, ENVELOPE_SIGNED;
    public static final Item PACKAGE, PACKAGE_SIGNED;

    /**
     * Electronic Update
     */
    public static final Item INK_CARTRIDGE;

    /**
     * Kitchen Update
     */
    public static final Item KNIFE, CUP, DRINK, BOTTLE, BOTTLE_DRINK, SOAP, BIG_BUCKET, SOAPY_WATER, SUPER_SOAPY_WATER;

    /**
     * Christmas Update
     */
    public static final Item LOG;

    /**
     * Outdoor Update
     */
    public static final Item SPATULA, SAUSAGE, SAUSAGE_COOKED, KEBAB, KEBAB_COOKED, CROWBAR;

    /**
     * Modern Update
     */
    public static final Item OLD_TV_REMOTE;
    public static final Item TV_REMOTE;

    /**
     * Food
     */
    public static final Item BREAD_SLICE, TOAST;

    /**
     * Misc
     */
    public static final Item COOL_PACK_WATER;
    public static final Item RECIPE_BOOK;
    public static final Item CRAYFISH;
    public static final Item CEILING_FAN_FANS;
    public static final Item WHEEL;
    public static final Item DOG_FOOD;
    public static final Item CONSERVE_CAN;
    public static final Item LIGHT_BULB;
    public static final Item TRASH_BAG;
    public static final Item COIN;
    public static final Item CAT_FOOD;
    public static final Item BROOM;
    public static final Item CRAYLINKER;
    public static final Item NATURAL_PLASTIC_UNREFINED;
    public static final Item NATURAL_PLASTIC;
    public static final Item NATURAL_PLASTIC_FRAME;
    public static final Item SCREEN;
    public static final Item CIRCUIT_BOARD;
    public static final Item CONTROLLER_UNIT;
    public static final Item DDR5_RAM;
    public static final Item CPU_DESKTOP;
    public static final Item GPU_LAPTOP;
    public static final Item WIFI_CARD;
    public static final Item MOTHERBOARD_DESKTOP;
    public static final Item FLASH_CHIP;
    public static final Item GOLDEN_WIRE;
    public static final Item ETHERNET_CABLE;
    public static final Item PRINTER_CARRIAGE;
    public static final Item SMALL_ELECTRIC_MOTOR;
    public static final Item BATTERY;
    public static final Item HARD_DRIVE;
    public static final Item HARD_DRIVE_PLATTER;
    public static final Item SOLID_STATE_DRIVE;

    /**
     * Nostalgia Update
     */
    public static final Item CUP_OLD;


    static
    {
        COOL_PACK = new ItemGeneric().setTranslationKey("item_cool_pack").setRegistryName("item_cool_pack");
        COOL_PACK_WATER = new ItemGeneric().setTranslationKey("item_cool_pack_water").setRegistryName("item_cool_pack_water");
        INK_CARTRIDGE = new ItemGeneric().setTranslationKey("item_ink_cartridge").setRegistryName("item_ink_cartridge");
        ENVELOPE = new ItemEnvelope().setTranslationKey("item_envelope").setRegistryName("item_envelope");
        ENVELOPE_SIGNED = new ItemEnvelopeSigned().setTranslationKey("item_envelope_signed").setRegistryName("item_envelope_signed");
        PACKAGE = new ItemPackage().setTranslationKey("item_package").setRegistryName("item_package");
        PACKAGE_SIGNED = new ItemPackageSigned().setTranslationKey("item_package_signed").setRegistryName("item_package_signed");
        HAMMER = new ItemGeneric().setTranslationKey("item_hammer").setRegistryName("item_hammer");
        KNIFE = new ItemKnife().setMaxDamage(100).setTranslationKey("item_knife").setRegistryName("item_knife");
        CUP = new ItemCup(false).setTranslationKey("item_cup").setRegistryName("item_cup");
        DRINK = new ItemCup(true).setTranslationKey("item_drink").setRegistryName("item_drink");
        CUP_OLD = new ItemCup(false).setTranslationKey("item_cup_old").setRegistryName("item_cup_old");
        BOTTLE = new ItemBottle(false).setTranslationKey("item_bottle").setRegistryName("item_bottle");
        BOTTLE_DRINK = new ItemBottle(true).setTranslationKey("item_bottle_drink").setRegistryName("item_bottle_drink");
        SOAP = new ItemGeneric().setTranslationKey("item_soap").setRegistryName("item_soap");
        BIG_BUCKET = new ItemGeneric().setTranslationKey("item_big_bucket").setRegistryName("item_big_bucket").setMaxStackSize(1);
        SOAPY_WATER = new ItemGeneric().setTranslationKey("item_soap_water").setRegistryName("item_soap_water").setMaxStackSize(1);
        SUPER_SOAPY_WATER = new ItemGeneric().setTranslationKey("item_super_soap_water").setRegistryName("item_super_soap_water").setMaxStackSize(1);
        RECIPE_BOOK = new ItemRecipeBook().setTranslationKey("item_recipe_book").setRegistryName("item_recipe_book");
        CRAYFISH = new Item().setTranslationKey("item_crayfish").setRegistryName("item_crayfish").setMaxStackSize(1);
        LOG = new ItemLog(FurnitureBlocks.FIRE_PIT_OFF).setTranslationKey("item_log").setRegistryName("item_log").setMaxStackSize(16);
        SPATULA = new ItemGeneric().setTranslationKey("item_spatula").setRegistryName("item_spatula").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        FLESH = new ItemFood(1, 2, false).setTranslationKey("item_flesh").setRegistryName("item_flesh").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        COOKED_FLESH = new ItemFood(4, 4, false).setTranslationKey("item_flesh_cooked").setRegistryName("item_flesh_cooked").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        SAUSAGE = new ItemFood(1, false).setTranslationKey("item_sausage").setRegistryName("item_sausage").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        SAUSAGE_COOKED = new ItemFood(4, false).setTranslationKey("item_sausage_cooked").setRegistryName("item_sausage_cooked").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        KEBAB = new ItemFood(1, false).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.3F).setTranslationKey("item_kebab").setRegistryName("item_kebab").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        KEBAB_COOKED = new ItemFood(4, false).setTranslationKey("item_kebab_cooked").setRegistryName("item_kebab_cooked").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        BREAD_SLICE = new ItemFood(2, false).setTranslationKey("item_bread_slice").setRegistryName("item_bread_slice").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        TOAST = new ItemFood(4, false).setTranslationKey("item_toast").setRegistryName("item_toast").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        CROWBAR = new ItemGeneric().setTranslationKey("item_crow_bar").setRegistryName("item_crow_bar").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        CEILING_FAN_FANS = new ItemGeneric().setTranslationKey("ceiling_fan_fans").setRegistryName("ceiling_fan_fans");
        OLD_TV_REMOTE = new ItemOldTvRemote();
        TV_REMOTE = new ItemTVRemote();
        WHEEL = new ItemGeneric().setTranslationKey("item_wheel").setRegistryName("item_wheel");
        DOG_FOOD = new ItemGeneric().setTranslationKey("item_dog_food").setRegistryName("item_dog_food");
        CAT_FOOD = new ItemGeneric().setTranslationKey("item_cat_food").setRegistryName("item_cat_food");
        CONSERVE_CAN = new ItemGeneric().setTranslationKey("item_conserve_can").setRegistryName("item_conserve_can");
        LIGHT_BULB = new ItemGeneric().setTranslationKey("item_light_bulb").setRegistryName("item_light_bulb");
        TRASH_BAG = new ItemGeneric().setTranslationKey("item_trashbag").setRegistryName("item_trashbag");
        COIN = new ItemGeneric().setTranslationKey("item_coin").setRegistryName("item_coin");
        BROOM = new ItemBroom().setTranslationKey("item_broom").setRegistryName("item_broom");
        CRAYLINKER = new ItemGeneric().setTranslationKey("item_cray_linker").setRegistryName("item_cray_linker");
        NATURAL_PLASTIC_UNREFINED = new ItemGeneric().setTranslationKey("item_natural_plastic_unrefined").setRegistryName("item_natural_plastic_unrefined");
        NATURAL_PLASTIC = new ItemGeneric().setTranslationKey("item_natural_plastic").setRegistryName("item_natural_plastic");
        NATURAL_PLASTIC_FRAME = new ItemGeneric().setTranslationKey("item_natural_plastic_frame").setRegistryName("item_natural_plastic_frame");
        SCREEN = new ItemGeneric().setTranslationKey("item_screen").setRegistryName("item_screen");
        CIRCUIT_BOARD = new ItemGeneric().setTranslationKey("item_circuit_board").setRegistryName("item_circuit_board");
        CONTROLLER_UNIT = new ItemGeneric().setTranslationKey("item_controller_unit").setRegistryName("item_controller_unit");
        DDR5_RAM = new ItemDesktopMotherboard.Component("item_ddr5_ram");
        CPU_DESKTOP = new ItemDesktopMotherboard.Component("item_cpu_desktop");
        GPU_LAPTOP = new ItemDesktopMotherboard.Component("item_gpu_laptop");
        WIFI_CARD = new ItemDesktopMotherboard.Component("item_wifi_card");
        MOTHERBOARD_DESKTOP = new ItemDesktopMotherboard();
        FLASH_CHIP = new ItemGeneric().setTranslationKey("item_flash_chip").setRegistryName("item_flash_chip");
        GOLDEN_WIRE = new ItemGeneric().setTranslationKey("item_golden_wire").setRegistryName("item_golden_wire");
        ETHERNET_CABLE = new ItemGeneric().setTranslationKey("item_ethernet_cable").setRegistryName("item_ethernet_cable");
        PRINTER_CARRIAGE = new ItemGeneric().setTranslationKey("item_printer_carriage").setRegistryName("item_printer_carriage");
        SMALL_ELECTRIC_MOTOR = new ItemGeneric().setTranslationKey("item_small_electric_motor").setRegistryName("item_small_electric_motor");
        BATTERY = new ItemGeneric().setTranslationKey("item_battery").setRegistryName("item_battery");
        HARD_DRIVE = new ItemGeneric().setTranslationKey("item_hard_drive").setRegistryName("item_hard_drive");
        HARD_DRIVE_PLATTER = new ItemGeneric().setTranslationKey("item_hard_drive_platter").setRegistryName("item_hard_drive_platter");
        SOLID_STATE_DRIVE = new ItemGeneric().setTranslationKey("item_solid_state_drive").setRegistryName("item_solid_state_drive");
    }

    public static void register()
    {
        registerItem(COOL_PACK);
        registerItem(COOL_PACK_WATER);
        registerItem(HAMMER);
        registerItem(ENVELOPE);
        registerItem(ENVELOPE_SIGNED);
        registerItem(PACKAGE);
        registerItem(PACKAGE_SIGNED);
        registerItem(INK_CARTRIDGE);
        registerItem(KNIFE);
        registerItem(CUP);
        registerItem(DRINK);
        registerItem(CUP_OLD);
        registerItem(BOTTLE);
        registerItem(BOTTLE_DRINK);
        registerItem(SOAP);
        registerItem(BIG_BUCKET);
        registerItem(SOAPY_WATER);
        registerItem(SUPER_SOAPY_WATER);
        registerItem(RECIPE_BOOK);
        registerItem(CRAYFISH);
        registerItem(LOG);
        registerItem(SPATULA);
        registerItem(FLESH);
        registerItem(COOKED_FLESH);
        registerItem(SAUSAGE);
        registerItem(SAUSAGE_COOKED);
        registerItem(KEBAB);
        registerItem(KEBAB_COOKED);
        registerItem(BREAD_SLICE);
        registerItem(TOAST);
        registerItem(CROWBAR);
        registerItem(CEILING_FAN_FANS);
        registerItem(OLD_TV_REMOTE);
        registerItem(TV_REMOTE);
        registerItem(WHEEL);
        registerItem(DOG_FOOD);
        registerItem(CAT_FOOD);
        registerItem(CONSERVE_CAN);
        registerItem(LIGHT_BULB);
        registerItem(TRASH_BAG);
        registerItem(COIN);
        registerItem(BROOM);
        registerItem(CRAYLINKER);
        registerItem(NATURAL_PLASTIC_UNREFINED);
        registerItem(NATURAL_PLASTIC);
        registerItem(NATURAL_PLASTIC_FRAME);
        registerItem(SCREEN);
        registerItem(CIRCUIT_BOARD);
        registerItem(CONTROLLER_UNIT);
        registerItem(DDR5_RAM);
        registerItem(CPU_DESKTOP);
        registerItem(GPU_LAPTOP);
        registerItem(WIFI_CARD);
        registerItem(MOTHERBOARD_DESKTOP);
        registerItem(FLASH_CHIP);
        registerItem(GOLDEN_WIRE);
        registerItem(ETHERNET_CABLE);
        registerItem(PRINTER_CARRIAGE);
        registerItem(SMALL_ELECTRIC_MOTOR);
        registerItem(BATTERY);
        registerItem(HARD_DRIVE);
        registerItem(HARD_DRIVE_PLATTER);
        registerItem(SOLID_STATE_DRIVE);
    }

    private static void registerItem(Item item)
    {
        RegistrationHandler.Items.add(item);
    }
}
