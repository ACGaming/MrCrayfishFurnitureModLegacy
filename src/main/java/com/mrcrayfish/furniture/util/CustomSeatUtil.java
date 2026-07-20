package com.mrcrayfish.furniture.util;

import com.mrcrayfish.furniture.entity.EntityCustomSeat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Author: MrCrayfish
 */
public class CustomSeatUtil
{
    public static void createSeatAndSit(World worldIn, BlockPos pos, EntityPlayer playerIn, double yOffset)
    {
        List<EntityCustomSeat> seats = worldIn.getEntitiesWithinAABB(EntityCustomSeat.class, new AxisAlignedBB(pos));
        if(!seats.isEmpty())
        {
            EntityCustomSeat seat = seats.get(0);
            if(seat.getRidingEntity() == null)
            {
                playerIn.startRiding(seat);
            }
        }
        else
        {
            EntityCustomSeat seat = new EntityCustomSeat(worldIn, pos, yOffset);
            worldIn.spawnEntity(seat);
            playerIn.startRiding(seat);
        }
    }
}
