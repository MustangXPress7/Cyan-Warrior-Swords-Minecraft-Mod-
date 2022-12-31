package com.raptorbk.CyanWarriorSwordsRedux.events.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class EssencesStructureAdditionModifier extends LootModifier {
    private final Item addition;

    public EssencesStructureAdditionModifier(ILootCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(addition.getDefaultInstance());
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<EssencesStructureAdditionModifier> {

        @Override
        public EssencesStructureAdditionModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(JSONUtils.getAsString(object, "item")));
            return new EssencesStructureAdditionModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(EssencesStructureAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}
