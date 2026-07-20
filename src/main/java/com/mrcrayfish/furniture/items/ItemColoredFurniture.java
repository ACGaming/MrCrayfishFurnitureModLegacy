package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

/**
 * Author: MrCrayfish
 */
public class ItemColoredFurniture extends ItemBlock implements SubItems
{
    public ItemColoredFurniture(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return "tile." + this.getBlock().getRegistryName().getPath()
                + "_" + EnumDyeColor.values()[stack.getItemDamage()].getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(isInCreativeTab(tab))
        {
            for(EnumDyeColor color : EnumDyeColor.values())
            {
                items.add(new ItemStack(this, 1, color.getMetadata()));
            }
        }
    }

    @Override
    public NonNullList<ResourceLocation> getModels()
    {
        NonNullList<ResourceLocation> models = NonNullList.create();

        for(EnumDyeColor color : EnumDyeColor.values())
        {
            models.add(new ResourceLocation(
                    Reference.MOD_ID,
                    this.getBlock().getRegistryName().getPath()
                            + "/" + color.getName()
            ));
        }

        return models;
    }
}