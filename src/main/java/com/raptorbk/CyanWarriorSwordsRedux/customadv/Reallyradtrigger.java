package com.raptorbk.CyanWarriorSwordsRedux.customadv;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.raptorbk.CyanWarriorSwordsRedux.swords.CyanType.CYAN_SWORD;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

public class Reallyradtrigger extends SimpleCriterionTrigger<Reallyradtrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("cwsr","really_rad_trigger");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Reallyradtrigger.Instance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext conditionsParser) {
        return new Reallyradtrigger.Instance(entityPredicate);
    }


    public void trigger(ServerPlayer entity) {
        this.trigger(entity, (instance) -> {
            return instance.test(entity.getMainHandItem().getItem(),entity.getOffhandItem().getItem());
        });
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(EntityPredicate.Composite player) {
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