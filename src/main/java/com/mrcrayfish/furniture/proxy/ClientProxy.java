package com.mrcrayfish.furniture.proxy;

import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.client.GifCache;
import com.mrcrayfish.furniture.handler.ClientEvents;
import com.mrcrayfish.furniture.handler.ConfigurationHandler;
import com.mrcrayfish.furniture.handler.InputHandler;
import com.mrcrayfish.furniture.init.FurnitureBlocks;
import com.mrcrayfish.furniture.init.FurnitureItems;
import com.mrcrayfish.furniture.integration.biomesoplenty.FurnitureBlocksBOP;
import com.mrcrayfish.furniture.render.tileentity.*;
import com.mrcrayfish.furniture.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class ClientProxy extends CommonProxy
{
    public static boolean rendering = false;
    public static Entity renderEntity = null;
    public static Entity backupEntity = null;

    @Override
    public void init()
    {
        registerColorHandlers();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCookieJar.class, new CookieRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlate.class, new PlateRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToaster.class, new ToastRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChoppingBoard.class, new ChoppingBoardRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlender.class, new BlenderRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMicrowave.class, new MicrowaveRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWashingMachine.class, new WashingMachineRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCup.class, new CupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTree.class, new TreeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMirror.class, new MirrorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOven.class, new OvenRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrill.class, new GrillRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEsky.class, new EskyRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorMat.class, new DoorMatRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCeilingFan.class, new CeilingFanRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityModernSlidingDoor.class, new ModernSlidingDoorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDigitalClock.class, new DigitalClockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTV.class, new TVRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoffeeTable.class, new CoffeeTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPhotoFrame.class, new PhotoFrameRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGamingChair.class, new GamingChairRenderer());

        MinecraftForge.EVENT_BUS.register(GifCache.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new MirrorRenderer());
    }

    public void registerColorHandlers()
    {
        FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler((stack, tintIndex) ->
        {
            if(tintIndex == 1)
            {
                if(stack.hasTagCompound())
                {
                    if(stack.getTagCompound().hasKey("Colour"))
                    {
                        int[] colour = stack.getTagCompound().getIntArray("Colour");
                        Color color = new Color(colour[0], colour[1], colour[2]);
                        return color.getRGB();
                    }
                }
            }
            return 16777215;
        }, FurnitureItems.DRINK);
        FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler((stack, tintIndex) ->
        {
            if(tintIndex == 1)
            {
                if(stack.hasTagCompound())
                {
                    if(stack.getTagCompound().hasKey("Colour"))
                    {
                        int[] colour = stack.getTagCompound().getIntArray("Colour");
                        Color color = new Color(colour[0], colour[1], colour[2]);
                        return color.getRGB();
                    }
                }
            }
            return 16777215;
        }, FurnitureItems.BOTTLE_DRINK);
        IItemColor hedgeItemColor = (stack, tintIndex) ->
        {
            IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
            return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, null, null, tintIndex);
        };
        IBlockColor hedgeBlockColorOld = (state, worldIn, pos, tintIndex) ->
        {
            Block block = state.getBlock();
            if(block == FurnitureBlocks.HEDGE_SPRUCE)
            {
                return ColorizerFoliage.getFoliageColorPine();
            }
            else if(block == FurnitureBlocks.HEDGE_BIRCH)
            {
                return ColorizerFoliage.getFoliageColorBirch();
            }
            else
            {
                return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
            }
        };
        IBlockColor hedgeBlockColorNew = (state, worldIn, pos, tintIndex) ->
                worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_OAK, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_SPRUCE, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_BIRCH, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_JUNGLE, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_ACACIA, hedgeBlockColorNew, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_DARK_OAK, hedgeBlockColorNew, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_OAK_OLD, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_SPRUCE_OLD, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_BIRCH_OLD, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_JUNGLE_OLD, hedgeBlockColorOld, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_ACACIA_OLD, hedgeBlockColorNew, hedgeItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.HEDGE_DARK_OAK_OLD, hedgeBlockColorNew, hedgeItemColor);
        IItemColor christmasItemColor = (stack, tintIndex) -> ColorizerFoliage.getFoliageColorPine();
        IBlockColor christmasBlockColor = (state, worldIn, pos, tintIndex) -> ColorizerFoliage.getFoliageColorPine();
        registerColorHandlerForBlock(FurnitureBlocks.TREE_BOTTOM, christmasBlockColor, christmasItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.TREE_TOP, christmasBlockColor, christmasItemColor);
        registerColorHandlerForBlock(FurnitureBlocks.WREATH, christmasBlockColor, christmasItemColor);

        if(Loader.isModLoaded("biomesoplenty") && ConfigurationHandler.integrationBiomesOPlenty)
        {
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_CHERRY, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_EBONY, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_EUCALYPTUS, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_MAHOGANY, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_MANGROVE, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_PALM, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_PINE, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_SACRED_OAK, hedgeBlockColorNew, hedgeItemColor);
            registerColorHandlerForBlock(FurnitureBlocksBOP.HEDGE_BIOMESOPLENTY_WILLOW, hedgeBlockColorNew, hedgeItemColor);
        }
    }

    public void registerColorHandlerForBlock(Block block, IBlockColor blockColor, IItemColor itemColor)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Item item = Item.getItemFromBlock(block);
        if (item != Items.AIR)
            mc.getItemColors().registerItemColorHandler(itemColor, item);
        mc.getBlockColors().registerBlockColorHandler(blockColor, block);
    }

    @Override
    public EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().player;
    }

    @Override
    public boolean isSinglePlayer()
    {
        return Minecraft.getMinecraft().isSingleplayer();
    }

    @Override
    public boolean isDedicatedServer()
    {
        return false;
    }

    @Override
    public void preInit()
    {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        //MinecraftForge.EVENT_BUS.register(new GuiDrawHandler());
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
    }

    @SubscribeEvent
    public void onClientWorldUnload(WorldEvent.Unload event)
    {
        if(event.getWorld() instanceof WorldClient)
        {
            MirrorRenderer.clearRegisteredMirrors();
        }
    }


    @SubscribeEvent
    public void onPrePlayerRender(RenderPlayerEvent.Pre event)
    {
        if(!rendering) return;

        if(event.getEntityPlayer() == renderEntity)
        {
            backupEntity = Minecraft.getMinecraft().getRenderManager().renderViewEntity;
            Minecraft.getMinecraft().getRenderManager().renderViewEntity = renderEntity;
        }
    }

    @SubscribeEvent
    public void onPostPlayerRender(RenderPlayerEvent.Post event)
    {
        if(!rendering) return;

        if(event.getEntityPlayer() == renderEntity)
        {
            Minecraft.getMinecraft().getRenderManager().renderViewEntity = backupEntity;
            renderEntity = null;
        }
    }
}
