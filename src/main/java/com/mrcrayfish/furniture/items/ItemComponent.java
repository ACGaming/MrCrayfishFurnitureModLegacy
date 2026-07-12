package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import net.minecraft.item.Item;

/**
 * Author: MrCrayfish and edited by MisterIceCat
 */

public class ItemComponent extends Item
{
    public ItemComponent(String id)
    {
        setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        setRegistryName(id);
        setTranslationKey(id);
    }
}
