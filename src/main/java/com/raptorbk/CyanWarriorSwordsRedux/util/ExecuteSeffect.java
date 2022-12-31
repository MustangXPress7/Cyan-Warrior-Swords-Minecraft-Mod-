package com.raptorbk.CyanWarriorSwordsRedux.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ExecuteSeffect implements SurroundEffect {

    public static BlockRayTraceResult raytraceFromEntity(Entity e, double distance, boolean fluids) {
        Vector3d Vector3d = e.getEyePosition(1);
        Vector3d Vector3d1 = e.getViewVector(1);
        Vector3d Vector3d2 = Vector3d.add(Vector3d1.x * distance, Vector3d1.y * distance, Vector3d1.z * distance);
        return e.level.clip(new RayTraceContext(Vector3d, Vector3d2, RayTraceContext.BlockMode.OUTLINE, fluids ? RayTraceContext.FluidMode.ANY : RayTraceContext.FluidMode.NONE, e));
    }


    public void TargetFromPlayer(World world, PlayerEntity entity, Block blk, Hand handIn){

            BlockRayTraceResult TargetPos = raytraceFromEntity(entity, 10, true);
            ItemStack currentSword = entity.getItemInHand(handIn);

            if (TargetPos.getType() != RayTraceResult.Type.BLOCK) {
                return;
            }

            /*
            if (!world.canMineBlockBody(entity, TargetPos.getPos())) {
                return;
            }*/

            if (!entity.mayUseItemAt(TargetPos.getBlockPos(), TargetPos.getDirection(), currentSword)) {
                return;
            }

            BlockPos pos0 = new BlockPos(TargetPos.getLocation().x() + 2, TargetPos.getLocation().y() + 2, TargetPos.getLocation().z() - 0);
            BlockPos pos1 = new BlockPos(TargetPos.getLocation().x() - 2, TargetPos.getLocation().y() + 2, TargetPos.getLocation().z() - 0);
            BlockPos pos2 = new BlockPos(TargetPos.getLocation().x() - 0, TargetPos.getLocation().y() + 2, TargetPos.getLocation().z() + 2);
            BlockPos pos3 = new BlockPos(TargetPos.getLocation().x() - 0, TargetPos.getLocation().y() + 2, TargetPos.getLocation().z() - 2);

            //Mid Blocks
            BlockPos pos4 = new BlockPos(TargetPos.getLocation().x() + 2, TargetPos.getLocation().y() + 1, TargetPos.getLocation().z() - 0);
            BlockPos pos5 = new BlockPos(TargetPos.getLocation().x() - 2, TargetPos.getLocation().y() + 1, TargetPos.getLocation().z() - 0);
            BlockPos pos6 = new BlockPos(TargetPos.getLocation().x() - 0, TargetPos.getLocation().y() + 1, TargetPos.getLocation().z() + 2);
            BlockPos pos7 = new BlockPos(TargetPos.getLocation().x() - 0, TargetPos.getLocation().y() + 1, TargetPos.getLocation().z() - 2);

            //Lower Blocks
            BlockPos pos8 = new BlockPos(TargetPos.getLocation().x() + 2, TargetPos.getLocation().y(), TargetPos.getLocation().z() - 0);
            BlockPos pos9 = new BlockPos(TargetPos.getLocation().x() - 2, TargetPos.getLocation().y(), TargetPos.getLocation().z() - 0);
            BlockPos pos10 = new BlockPos(TargetPos.getLocation().x() - 0, TargetPos.getLocation().y(), TargetPos.getLocation().z() + 2);
            BlockPos pos11 = new BlockPos(TargetPos.getLocation().x() - 0, TargetPos.getLocation().y(), TargetPos.getLocation().z() - 2);


            if (world.isEmptyBlock(pos0)) {
                world.setBlock(pos0, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos4)) {
                world.setBlock(pos4, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos8) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                world.setBlock(pos8, blk.defaultBlockState(),3);
            }


            if (world.isEmptyBlock(pos1)) {
                world.setBlock(pos1, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos5)) {
                world.setBlock(pos5, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos9) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                world.setBlock(pos9, blk.defaultBlockState(),3);
            }


            if (world.isEmptyBlock(pos2)) {
                world.setBlock(pos2, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos6)) {
                world.setBlock(pos6, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos10) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                world.setBlock(pos10, blk.defaultBlockState(),3);
            }


            if (world.isEmptyBlock(pos3)) {
                world.setBlock(pos3, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos7)) {
                world.setBlock(pos7, blk.defaultBlockState(),3);
            }

            if (world.isEmptyBlock(pos11) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                world.setBlock(pos11, blk.defaultBlockState(),3);
            }

        }



    public void SurroundPlayer(World world, PlayerEntity entity, Block blk){
        //Top Blocks
        BlockPos pos0 = new BlockPos(entity.getX(), entity.getY()+2, entity.getZ()-1);
        BlockPos pos1 = new BlockPos(entity.getX(), entity.getY()+2, entity.getZ()+1);
        BlockPos pos2 = new BlockPos(entity.getX()+1, entity.getY()+2, entity.getZ());
        BlockPos pos3 = new BlockPos(entity.getX()-1, entity.getY()+2, entity.getZ());

        //Mid Blocks
        BlockPos pos4 = new BlockPos(entity.getX(), entity.getY()+1, entity.getZ()-1);
        BlockPos pos5 = new BlockPos(entity.getX(), entity.getY()+1, entity.getZ()+1);
        BlockPos pos6 = new BlockPos(entity.getX()+1, entity.getY()+1, entity.getZ());
        BlockPos pos7 = new BlockPos(entity.getX()-1, entity.getY()+1, entity.getZ());

        //Lower Blocks
        BlockPos pos8 = new BlockPos(entity.getX(), entity.getY(), entity.getZ()-1);
        BlockPos pos9 = new BlockPos(entity.getX(), entity.getY(), entity.getZ()+1);
        BlockPos pos10 = new BlockPos(entity.getX()+1, entity.getY(), entity.getZ());
        BlockPos pos11 = new BlockPos(entity.getX()-1, entity.getY(), entity.getZ());

        if(world.isEmptyBlock(pos0)){
            System.out.println(world.getBlockState(pos0).getBlock());
            world.setBlock(pos0, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos1)){
            world.setBlock(pos1, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos2)){
            world.setBlock(pos2, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos3)){
            world.setBlock(pos3, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos4)){
            world.setBlock(pos4, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos5)){
            world.setBlock(pos5, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos6)){
            world.setBlock(pos6, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos7)){
            world.setBlock(pos7, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos8) || world.getBlockState(pos8).getBlock() == Blocks.SNOW){
            world.setBlock(pos8, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos9) || world.getBlockState(pos9).getBlock() == Blocks.SNOW){
            world.setBlock(pos9, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos10) || world.getBlockState(pos10).getBlock() == Blocks.SNOW){
            world.setBlock(pos10, blk.defaultBlockState(),3);
        }

        if(world.isEmptyBlock(pos11) || world.getBlockState(pos11).getBlock() == Blocks.SNOW){
            world.setBlock(pos11, blk.defaultBlockState(),3);
        }
    }



    @Override
    public void execute(World world, PlayerEntity entity, Hand handIn, Block blk) {
        TargetFromPlayer(world, entity, blk,handIn);
    }
}
