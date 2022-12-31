package com.raptorbk.CyanWarriorSwordsRedux.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ExecuteSeffect implements SurroundEffect{

    public static RayTraceResult raytraceFromEntity(World worldIn, Entity playerIn, boolean useLiquids, double range) {
        float f = playerIn.rotationPitch;
        float f1 = playerIn.rotationYaw;
        double d0 = playerIn.posX;
        double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
        double d2 = playerIn.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d3 = range;
        Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
        return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
    }



    public void TargetFromPlayer(World world, EntityPlayer entity, Block blk, EnumHand handIn){


            RayTraceResult TargetPos = raytraceFromEntity(world, entity, true, 8);
            //System.out.println(TargetPos);
            if (TargetPos != null) {
                ItemStack currentSword = entity.getHeldItem(handIn);

                if (TargetPos.typeOfHit != RayTraceResult.Type.BLOCK) {
                    return;
                }

                if (!world.canMineBlockBody(entity, TargetPos.getBlockPos())) {
                    return;
                }

                if (!entity.canPlayerEdit(TargetPos.getBlockPos(), TargetPos.sideHit, currentSword)) {
                    return;
                }

                BlockPos pos0 = new BlockPos(TargetPos.hitVec.x + 2, TargetPos.hitVec.y + 2, TargetPos.hitVec.z - 0);
                BlockPos pos1 = new BlockPos(TargetPos.hitVec.x - 2, TargetPos.hitVec.y + 2, TargetPos.hitVec.z - 0);
                BlockPos pos2 = new BlockPos(TargetPos.hitVec.x - 0, TargetPos.hitVec.y + 2, TargetPos.hitVec.z + 2);
                BlockPos pos3 = new BlockPos(TargetPos.hitVec.x - 0, TargetPos.hitVec.y + 2, TargetPos.hitVec.z - 2);

                //Mid Blocks
                BlockPos pos4 = new BlockPos(TargetPos.hitVec.x + 2, TargetPos.hitVec.y + 1, TargetPos.hitVec.z - 0);
                BlockPos pos5 = new BlockPos(TargetPos.hitVec.x - 2, TargetPos.hitVec.y + 1, TargetPos.hitVec.z - 0);
                BlockPos pos6 = new BlockPos(TargetPos.hitVec.x - 0, TargetPos.hitVec.y + 1, TargetPos.hitVec.z + 2);
                BlockPos pos7 = new BlockPos(TargetPos.hitVec.x - 0, TargetPos.hitVec.y + 1, TargetPos.hitVec.z - 2);

                //Lower Blocks
                BlockPos pos8 = new BlockPos(TargetPos.hitVec.x + 2, TargetPos.hitVec.y, TargetPos.hitVec.z - 0);
                BlockPos pos9 = new BlockPos(TargetPos.hitVec.x - 2, TargetPos.hitVec.y, TargetPos.hitVec.z - 0);
                BlockPos pos10 = new BlockPos(TargetPos.hitVec.x - 0, TargetPos.hitVec.y, TargetPos.hitVec.z + 2);
                BlockPos pos11 = new BlockPos(TargetPos.hitVec.x - 0, TargetPos.hitVec.y, TargetPos.hitVec.z - 2);


                if (world.isAirBlock(pos0)) {
                    world.setBlockState(pos0, blk.getDefaultState());
                }

                if (world.isAirBlock(pos4)) {
                    world.setBlockState(pos4, blk.getDefaultState());
                }

                if (world.isAirBlock(pos8) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                    world.setBlockState(pos8, blk.getDefaultState());
                }


                if (world.isAirBlock(pos1)) {
                    world.setBlockState(pos1, blk.getDefaultState());
                }

                if (world.isAirBlock(pos5)) {
                    world.setBlockState(pos5, blk.getDefaultState());
                }

                if (world.isAirBlock(pos9) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                    world.setBlockState(pos9, blk.getDefaultState());
                }


                if (world.isAirBlock(pos2)) {
                    world.setBlockState(pos2, blk.getDefaultState());
                }

                if (world.isAirBlock(pos6)) {
                    world.setBlockState(pos6, blk.getDefaultState());
                }

                if (world.isAirBlock(pos10) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                    world.setBlockState(pos10, blk.getDefaultState());
                }


                if (world.isAirBlock(pos3)) {
                    world.setBlockState(pos3, blk.getDefaultState());
                }

                if (world.isAirBlock(pos7)) {
                    world.setBlockState(pos7, blk.getDefaultState());
                }

                if (world.isAirBlock(pos11) || world.getBlockState(pos11).getBlock() == Blocks.SNOW || world.getBlockState(pos11).getBlock() == Blocks.GRASS) {
                    world.setBlockState(pos11, blk.getDefaultState());
                }

            }


    }

    public void SurroundPlayer(World world, EntityPlayer entity, Block blk){
        //Top Blocks
        BlockPos pos0 = new BlockPos(entity.posX, entity.posY+2, entity.posZ-1);
        BlockPos pos1 = new BlockPos(entity.posX, entity.posY+2, entity.posZ+1);
        BlockPos pos2 = new BlockPos(entity.posX+1, entity.posY+2, entity.posZ);
        BlockPos pos3 = new BlockPos(entity.posX-1, entity.posY+2, entity.posZ);

        //Mid Blocks
        BlockPos pos4 = new BlockPos(entity.posX, entity.posY+1, entity.posZ-1);
        BlockPos pos5 = new BlockPos(entity.posX, entity.posY+1, entity.posZ+1);
        BlockPos pos6 = new BlockPos(entity.posX+1, entity.posY+1, entity.posZ);
        BlockPos pos7 = new BlockPos(entity.posX-1, entity.posY+1, entity.posZ);

        //Lower Blocks
        BlockPos pos8 = new BlockPos(entity.posX, entity.posY, entity.posZ-1);
        BlockPos pos9 = new BlockPos(entity.posX, entity.posY, entity.posZ+1);
        BlockPos pos10 = new BlockPos(entity.posX+1, entity.posY, entity.posZ);
        BlockPos pos11 = new BlockPos(entity.posX-1, entity.posY, entity.posZ);

        if(world.isAirBlock(pos0)){
            System.out.println(world.getBlockState(pos0).getBlock());
            world.setBlockState(pos0, blk.getDefaultState());
        }

        if(world.isAirBlock(pos1)){
            world.setBlockState(pos1, blk.getDefaultState());
        }

        if(world.isAirBlock(pos2)){
            world.setBlockState(pos2, blk.getDefaultState());
        }

        if(world.isAirBlock(pos3)){
            world.setBlockState(pos3, blk.getDefaultState());
        }

        if(world.isAirBlock(pos4)){
            world.setBlockState(pos4, blk.getDefaultState());
        }

        if(world.isAirBlock(pos5)){
            world.setBlockState(pos5, blk.getDefaultState());
        }

        if(world.isAirBlock(pos6)){
            world.setBlockState(pos6, blk.getDefaultState());
        }

        if(world.isAirBlock(pos7)){
            world.setBlockState(pos7, blk.getDefaultState());
        }

        if(world.isAirBlock(pos8) || world.getBlockState(pos8).getBlock() == Blocks.SNOW){
            world.setBlockState(pos8, blk.getDefaultState());
        }

        if(world.isAirBlock(pos9) || world.getBlockState(pos9).getBlock() == Blocks.SNOW){
            world.setBlockState(pos9, blk.getDefaultState());
        }

        if(world.isAirBlock(pos10) || world.getBlockState(pos10).getBlock() == Blocks.SNOW){
            world.setBlockState(pos10, blk.getDefaultState());
        }

        if(world.isAirBlock(pos11) || world.getBlockState(pos11).getBlock() == Blocks.SNOW){
            world.setBlockState(pos11, blk.getDefaultState());
        }
    }



    @Override
    public void execute(World world, EntityPlayer entity, EnumHand handIn, Block blk) {
        TargetFromPlayer(world, entity, blk,handIn);
    }
}
