package com.codyrex.terravitae.server.item;

import com.codyrex.terravitae.Terravitae;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static void register() {
        try {
            for (Field field : ItemHandler.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Item) {
                    ItemHandler.registerItem((Item) obj);
                } else if (obj instanceof Item[]) {
                    for (Item item : (Item[]) obj) {
                        ItemHandler.registerItem(item);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Item registerItem(Item item) {
        String name = item.getUnlocalizedName().substring("item.".length());
        GameRegistry.register(item, new ResourceLocation(Terravitae.MODID, name));
        ITEMS.add(item);
        return item;
    }
}
