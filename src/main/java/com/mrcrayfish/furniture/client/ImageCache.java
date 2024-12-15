package com.mrcrayfish.furniture.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;

/**
 * Author: MrCrayfish
 */
@SideOnly(Side.CLIENT)
public final class ImageCache
{
    public static final ImageCache INSTANCE = new ImageCache();

    private final File cache;
    private Map<String, Texture> cacheMap = new ConcurrentHashMap<>();

    private ImageCache()
    {
        cache = new File(Minecraft.getMinecraft().mcDataDir, "photo-frame-cache");
        cache.mkdir();
        this.init();
    }

    private void init()
    {
        try
        {
            FileUtils.writeStringToFile(new File(cache, "!read-me.txt"), "This is a cache for GIFs that are played on the TV (in MrCrayfish's Furniture Mod) in order to speed up load time.\nIt is safe to delete the entire folder in case you are running out of space, however it will mean that all GIFs will have to be downloaded again.", "UTF-8");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Nullable
    public Texture get(String url)
    {
        if(url == null)
        {
            return null;
        }
        Texture texture = cacheMap.get(url);
        if(texture != null)
        {
            return texture;
        }
        synchronized(this)
        {
            return cacheMap.get(url);
        }
    }

    public void add(String url, File file)
    {
        if(cacheMap.containsKey(url))
        {
            return;
        }
        synchronized(this)
        {
            if(!cacheMap.containsKey(url))
            {
                Texture texture = new Texture(file);
                cacheMap.put(url, texture);
            }
        }
    }

    public boolean add(String url, byte[] data)
    {
        if(cacheMap.containsKey(url))
        {
            return true;
        }

        Texture texture;
        String id = DigestUtils.sha1Hex(url.getBytes());
        File image = new File(getCache(), id);

        synchronized(this)
        {
            if(cacheMap.containsKey(url))
            {
                return true;
            }
            cacheMap.put(url, texture = new Texture(image));
        }

        try
        {
            FileUtils.writeByteArrayToFile(image, data);
        }
        catch(IOException e)
        {
            MrCrayfishFurnitureMod.logger().warn(e);
        }
        Minecraft.getMinecraft().addScheduledTask(texture::update);
        return true;
    }

    private void tick()
    {
        cacheMap.values().forEach(Texture::update);
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if(event.phase == TickEvent.Phase.START)
        {
            this.tick();
        }
    }

    public boolean loadCached(String url)
    {
        if(cacheMap.containsKey(url))
        {
            return true;
        }

        String id = DigestUtils.sha1Hex(url.getBytes());
        File file = new File(getCache(), id);
        if(file.exists())
        {
            this.add(url, file);
            return true;
        }
        return false;
    }

    public boolean isCached(String url)
    {
        if(cacheMap.containsKey(url))
        {
            return true;
        }
        synchronized(this)
        {
            return cacheMap.containsKey(url);
        }
    }

    public File getCache()
    {
        cache.mkdir();
        return cache;
    }
}
