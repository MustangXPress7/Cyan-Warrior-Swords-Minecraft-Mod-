package com.raptorbk.CyanWarriorSwordsRedux.customadv;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Somethingelsetrigger implements ICriterionTrigger<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance>
{
    private static final ResourceLocation ID = new ResourceLocation("cwsr","something_else_trigger");
    private final Map<PlayerAdvancements, com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Listeners> listeners = Maps.<PlayerAdvancements, com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Listeners>newHashMap();

    public ResourceLocation getId()
    {
        return ID;
    }

    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance> listener)
    {
        com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Listeners usedtotemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (usedtotemtrigger$listeners == null)
        {
            usedtotemtrigger$listeners = new com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, usedtotemtrigger$listeners);
        }

        usedtotemtrigger$listeners.add(listener);
    }

    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance> listener)
    {
        com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Listeners usedtotemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (usedtotemtrigger$listeners != null)
        {
            usedtotemtrigger$listeners.remove(listener);

            if (usedtotemtrigger$listeners.isEmpty())
            {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
    {
        this.listeners.remove(playerAdvancementsIn);
    }

    public com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
    {
        ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
        return new com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance(itempredicate);
    }

    public void trigger(EntityPlayerMP player)
    {
        com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Listeners usedtotemtrigger$listeners = this.listeners.get(player.getAdvancements());

        if (usedtotemtrigger$listeners != null)
        {
            usedtotemtrigger$listeners.trigger();
        }
    }

    public static class Instance extends AbstractCriterionInstance
    {
        private final ItemPredicate item;

        public Instance(ItemPredicate item)
        {
            super(com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.ID);
            this.item = item;
        }

        public boolean test()
        {
            return true;
        }
    }

    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance>> listeners = Sets.<ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance>>newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty()
        {
            return this.listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance> listener)
        {
            this.listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance> listener)
        {
            this.listeners.remove(listener);
        }

        public void trigger()
        {
            List<ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance>> list = null;

            for (ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance> listener : this.listeners)
            {
                if (((com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance)listener.getCriterionInstance()).test())
                {
                    if (list == null)
                    {
                        list = Lists.<ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance>>newArrayList();
                    }

                    list.add(listener);
                }
            }

            if (list != null)
            {
                for (ICriterionTrigger.Listener<com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger.Instance> listener1 : list)
                {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}