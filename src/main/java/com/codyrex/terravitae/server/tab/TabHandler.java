package com.codyrex.terravitae.server.tab;

import com.codyrex.terravitae.server.block.BlockHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabHandler {
    public static final CreativeTabs PLANTS = new CreativeTabs("terravitae.plants") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlockHandler.SAND_RYEGRASS);
        }
    };

}
