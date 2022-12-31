package com.raptorbk.CyanWarriorSwordsRedux.customadv;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.raptorbk.CyanWarriorSwordsRedux.swords.CyanType.CYAN_SWORD;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class Reallyradtrigger extends AbstractCriterionTrigger<Reallyradtrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("cwsr","really_rad_trigger");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Reallyradtrigger.Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser conditionsParser) {
        return new Reallyradtrigger.Instance(entityPredicate);
    }


    public void trigger(ServerPlayerEntity entity) {
        this.trigger(entity, (instance) -> {
            return instance.test(entity.getMainHandItem().getItem(),entity.getOffhandItem().getItem());
        });
    }

    public static class Instance extends CriterionInstance {
        public Instance(EntityPredicate.AndPredicate player) {
            super(Reallyradtrigger.ID, player);
        }

        public boolean test(Item mainI, Item offI) {
            if(mainI instanceof CYAN_SWORD && offI instanceof CYAN_SWORD){
                return true;
            }else{
                return false;
            }
        }
    }
}