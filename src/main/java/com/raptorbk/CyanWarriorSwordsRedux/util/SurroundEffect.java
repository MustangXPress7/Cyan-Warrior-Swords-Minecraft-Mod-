package com.raptorbk.CyanWarriorSwordsRedux.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface SurroundEffect {
    void execute(World world, PlayerEntity entity, Hand handIn,Block blk);
}
