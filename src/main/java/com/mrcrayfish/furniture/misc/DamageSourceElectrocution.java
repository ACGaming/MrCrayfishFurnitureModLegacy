package com.mrcrayfish.furniture.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceElectrocution extends DamageSource
{
    public DamageSourceElectrocution(String damageTypeIn)
    {
        super(damageTypeIn);
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
    {
        return new TextComponentTranslation("death.block.electrocution", entityLivingBaseIn.getDisplayName());
    }
}
