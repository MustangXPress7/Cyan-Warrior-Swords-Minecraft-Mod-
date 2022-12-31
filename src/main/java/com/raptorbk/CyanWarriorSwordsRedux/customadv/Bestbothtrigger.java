package com.raptorbk.CyanWarriorSwordsRedux.customadv;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class Bestbothtrigger extends AbstractCriterionTrigger<Bestbothtrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("cwsr","best_both_trigger");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Bestbothtrigger.Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser conditionsParser) {
        return new Bestbothtrigger.Instance(entityPredicate);
    }

    public void trigger(ServerPlayerEntity entity) {
        this.trigger(entity, (instance) -> {
            return instance.test();
        });
    }

    public static class Instance extends CriterionInstance {
        public Instance(EntityPredicate.AndPredicate player) {
            super(Bestbothtrigger.ID, player);
        }

        public boolean test() {
            return true;
        }
    }
}