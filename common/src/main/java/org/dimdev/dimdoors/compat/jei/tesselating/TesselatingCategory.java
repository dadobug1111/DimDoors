package org.dimdev.dimdoors.compat.jei.tesselating;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.dimdev.dimdoors.DimensionalDoors;
import org.dimdev.dimdoors.block.ModBlocks;
import org.dimdev.dimdoors.recipe.TesselatingRecipe;
import org.jetbrains.annotations.Nullable;

public class TesselatingCategory implements IRecipeCategory<TesselatingRecipe> {
    public static final RecipeType<TesselatingRecipe> TYPE = RecipeType.create(DimensionalDoors.MOD_ID, "tesselating", TesselatingRecipe.class);
    public static final ResourceLocation TEXTURE = new ResourceLocation(DimensionalDoors.MOD_ID, "textures/screen/container/tesselating_loom.png");

    private final IDrawable background;
    private final IDrawable icon;

    public TesselatingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.TESSELATING_LOOM.get()));

    }

    @Override
    public RecipeType<TesselatingRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("category.dimdoors.tesselating");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TesselatingRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        for(int x = 0; x < 3; ++x) {
            for(int y = 0; y < 3; ++y) {
                if(x + (y * 3) <ingredients.size())builder.addInputSlot(30 + x * 18, 17 + y * 18).addIngredients(ingredients.get(x + (y * 3)));
            }
        }
        builder.addOutputSlot( 124, 35).addItemStack(recipe.getResultItem(null));
    }
}
