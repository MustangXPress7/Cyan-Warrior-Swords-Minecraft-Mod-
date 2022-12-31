package com.raptorbk.CyanWarriorSwordsRedux.common.recipe.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class SerializerHelper {
    private SerializerHelper()
    {
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static JsonElement serializeItemStack(@Nonnull ItemStack stack)
    {
        JsonObject json = new JsonObject();
        json.addProperty("item", stack.getItem().getRegistryName().toString());
        if (stack.getCount() > 1)
        {
            json.addProperty("count", stack.getCount());
        }
        if (stack.hasTag())
        {
            json.addProperty("nbt", stack.getTag().toString());
        }
        return json;
    }

    public static JsonElement serializefloats(String PropertyName, @Nonnull float FloatValue){
        JsonObject json = new JsonObject();
        json.addProperty(PropertyName, FloatValue);
        return json;
    }
    public static JsonElement serializeints(String PropertyName, @Nonnull int IntValue){
        JsonObject json = new JsonObject();
        json.addProperty(PropertyName,IntValue);
        return json;
    }
}
