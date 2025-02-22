package org.dimdev.dimdoors.compat.jei;


import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.dimdev.dimdoors.DimensionalDoors;
import org.dimdev.dimdoors.block.ModBlocks;
import org.dimdev.dimdoors.client.screen.TesselatingLoomScreen;
import org.dimdev.dimdoors.compat.jei.tesselating.TesselatingCategory;
import org.dimdev.dimdoors.recipe.ModRecipeTypes;
import org.dimdev.dimdoors.recipe.TesselatingRecipe;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class TesselatingJeiCompatClient implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(DimensionalDoors.MOD_ID, "jei_plugin");

    @Nullable
    private TesselatingCategory tesselatingCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        this.tesselatingCategory = new TesselatingCategory(helper);
        registration.addRecipeCategories(this.tesselatingCategory);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

        List<TesselatingRecipe> tesselatingRecipes = new ArrayList<>(manager.getAllRecipesFor(ModRecipeTypes.SHAPED_TESSELATING.get()));
        tesselatingRecipes.addAll(manager.getAllRecipesFor(ModRecipeTypes.SHAPELESS_TESSELATING.get()));

        registration.addRecipes(TesselatingCategory.TYPE, tesselatingRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlocks.TESSELATING_LOOM.get(), TesselatingCategory.TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(TesselatingLoomScreen.class, 89, 34, 22, 16, TesselatingCategory.TYPE);
    }
}
