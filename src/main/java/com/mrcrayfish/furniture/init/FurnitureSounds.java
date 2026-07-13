package com.mrcrayfish.furniture.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class FurnitureSounds
{
    public static SoundEvent bin_close;
    public static SoundEvent bin_open;
    public static SoundEvent blender;
    public static SoundEvent boing;
    public static SoundEvent food_dispenser_fill;
    public static SoundEvent food_dispenser_used;
    public static SoundEvent bowl_fill;
    public static SoundEvent bounce;
    public static SoundEvent cabinet_close;
    public static SoundEvent cabinet_open;
    public static SoundEvent carton;
    public static SoundEvent channel_cooking;
    public static SoundEvent channel_dogfood;
    public static SoundEvent channel_heman;
    public static SoundEvent channel_news;
    public static SoundEvent channel_sam_tabor;
    public static SoundEvent channel_switch;
    public static SoundEvent computer;
    public static SoundEvent old_computer;
    public static SoundEvent dishwasher;
    public static SoundEvent dishwasher_close;
    public static SoundEvent dishwasher_open;
    public static SoundEvent door_bell;
    public static SoundEvent esky_close;
    public static SoundEvent esky_open;
    public static SoundEvent fart;
    public static SoundEvent fire_alarm;
    public static SoundEvent flush;
    public static SoundEvent fridge_close;
    public static SoundEvent fridge_open;
    public static SoundEvent kitchen_counter_close;
    public static SoundEvent kitchen_counter_open;
    public static SoundEvent knife_chop;
    public static SoundEvent light_switch;
    public static SoundEvent microwave_close;
    public static SoundEvent microwave_finish;
    public static SoundEvent microwave_open;
    public static SoundEvent microwave_running;
    public static SoundEvent oven_close;
    public static SoundEvent oven_open;
    public static SoundEvent printer;
    public static SoundEvent printer_paper;
    public static SoundEvent service_bell;
    public static SoundEvent shower;
    public static SoundEvent sizzle;
    public static SoundEvent sliding_door_close;
    public static SoundEvent sliding_door_open;
    public static SoundEvent tap_close;
    public static SoundEvent tap_open;
    public static SoundEvent toaster_down;
    public static SoundEvent toaster_up;
    public static SoundEvent tv_remote;
    public static SoundEvent washing_machine;
    public static SoundEvent wheelie_bin_close;
    public static SoundEvent wheelie_bin_open;
    public static SoundEvent white_noise;
    public static SoundEvent zap;

    public static void register()
    {
        bin_close = registerSound("cfm:bin_close");
        bin_open = registerSound("cfm:bin_open");
        blender = registerSound("cfm:blender");
        boing = registerSound("cfm:boing");
        food_dispenser_fill = registerSound("cfm:food_dispenser_fill");
        food_dispenser_used = registerSound("cfm:food_dispenser_used");
        bowl_fill = registerSound("cfm:bowl_fill");
        bounce = registerSound("cfm:bounce");
        cabinet_close = registerSound("cfm:cabinet_close");
        cabinet_open = registerSound("cfm:cabinet_open");
        carton = registerSound("cfm:carton");
        channel_cooking = registerSound("cfm:channel_cooking");
        channel_dogfood = registerSound("cfm:channel_dogfood");
        channel_heman = registerSound("cfm:channel_heman");
        channel_news = registerSound("cfm:channel_news");
        channel_sam_tabor = registerSound("cfm:channel_sam_tabor");
        channel_switch = registerSound("cfm:channel_switch");
        computer = registerSound("cfm:computer");
        old_computer = registerSound("cfm:old_computer");
        dishwasher = registerSound("cfm:dishwasher");
        dishwasher_close = registerSound("cfm:dishwasher_close");
        dishwasher_open = registerSound("cfm:dishwasher_open");
        door_bell = registerSound("cfm:door_bell");
        esky_close = registerSound("cfm:esky_close");
        esky_open = registerSound("cfm:esky_open");
        fart = registerSound("cfm:fart");
        fire_alarm = registerSound("cfm:fire_alarm");
        flush = registerSound("cfm:flush");
        fridge_close = registerSound("cfm:fridge_close");
        fridge_open = registerSound("cfm:fridge_open");
        kitchen_counter_close = registerSound("cfm:kitchen_counter_close");
        kitchen_counter_open = registerSound("cfm:kitchen_counter_open");
        knife_chop = registerSound("cfm:knife_chop");
        light_switch = registerSound("cfm:light_switch");
        microwave_close = registerSound("cfm:microwave_close");
        microwave_finish = registerSound("cfm:microwave_finish");
        microwave_open = registerSound("cfm:microwave_open");
        microwave_running = registerSound("cfm:microwave_running");
        oven_close = registerSound("cfm:oven_close");
        oven_open = registerSound("cfm:oven_open");
        printer = registerSound("cfm:printer");
        printer_paper = registerSound("cfm:printer_paper");
        service_bell = registerSound("cfm:service_bell");
        shower = registerSound("cfm:shower");
        sizzle = registerSound("cfm:sizzle");
        sliding_door_close = registerSound("cfm:sliding_door_close");
        sliding_door_open = registerSound("cfm:sliding_door_open");
        tap_close = registerSound("cfm:tap_close");
        tap_open = registerSound("cfm:tap_open");
        toaster_down = registerSound("cfm:toaster_down");
        toaster_up = registerSound("cfm:toaster_up");
        tv_remote = registerSound("cfm:tv_remote");
        washing_machine = registerSound("cfm:washing_machine");
        wheelie_bin_close = registerSound("cfm:wheelie_bin_close");
        wheelie_bin_open = registerSound("cfm:wheelie_bin_open");
        white_noise = registerSound("cfm:white_noise");
        zap = registerSound("cfm:zap");
    }

    private static SoundEvent registerSound(String soundNameIn)
    {
        ResourceLocation resource = new ResourceLocation(soundNameIn);
        SoundEvent sound = new SoundEvent(resource).setRegistryName(soundNameIn);
        RegistrationHandler.Sounds.add(sound);
        return sound;
    }
}
