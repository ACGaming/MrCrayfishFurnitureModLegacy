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
public class CustomSeatUtil {
    public static void createSeatAndSit(World worldIn, BlockPos pos, EntityPlayer playerIn, double yOffset) {
        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            List<EntityCustomSeat> seats = worldIn.getEntitiesWithinAABB(EntityCustomSeat.class, new AxisAlignedBB(pos).grow(1D));

            for (EntityCustomSeat seat : seats) {
                if (seat.blockPosX == pos.getX() && seat.blockPosY == pos.getY() && seat.blockPosZ == pos.getZ()) {
                    if (!seat.isBeingRidden()) {
                        playerIn.startRiding(seat);
                    }
                    return;
                }
            }

            EntityCustomSeat seat = new EntityCustomSeat(worldIn, pos, yOffset);
            worldIn.spawnEntity(seat);
            playerIn.startRiding(seat);
        }
    }
}