package com.raptorbk.CyanWarriorSwordsRedux;

import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ACTIVE_SYNERGY_TOTEM extends Item {
    public ACTIVE_SYNERGY_TOTEM() {
        super(new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }


    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack x=entity.getItemInHand(handIn);
        Map<Enchantment, Integer> xEnc= EnchantmentHelper.getEnchantments(x);

        if(entity.isCrouching()){
            if(xEnc.containsKey(RegistryHandler.inh_ENCHANTMENT.get())){
                xEnc.remove(RegistryHandler.inh_ENCHANTMENT.get());
                EnchantmentHelper.setEnchantments(xEnc,x);
                ITextComponent chatMessage=new TranslationTextComponent("chat.cwsr.usage.deactivation.inh");
                if(!world.isClientSide()) {
                    entity.sendMessage(chatMessage,entity.getUUID());
                }
            }else{
                ITextComponent chatMessage=new TranslationTextComponent("chat.cwsr.usage.activation.inh");
                if(!world.isClientSide()) {
                    entity.sendMessage(chatMessage,entity.getUUID());
                }
                x.enchant(RegistryHandler.inh_ENCHANTMENT.get(),1);
            }

            entity.getCooldowns().addCooldown(x.getItem(), 40);
            return new ActionResult<>(ActionResultType.SUCCESS, x);
        }

        ItemStack totemStack = new ItemStack(RegistryHandler.synergy_TOTEM.get(),1);
        if(xEnc.containsKey(RegistryHandler.inh_ENCHANTMENT.get())){
            totemStack.enchant(RegistryHandler.inh_ENCHANTMENT.get(),1);
        }

        ITextComponent chatMessage=new TranslationTextComponent("chat.cwsr.usage.deactivation.dw");
        if(!world.isClientSide()) {
            entity.sendMessage(chatMessage,entity.getUUID());
        }
        return new ActionResult<>(ActionResultType.SUCCESS, totemStack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.synergy"));
    }
}
