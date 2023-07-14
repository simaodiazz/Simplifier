package item;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder() {
        this.itemStack = new ItemStack(Material.AIR);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    private void consumeMeta(Consumer<ItemMeta> meta) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        meta.accept(itemMeta);
        itemStack.setItemMeta(itemMeta);
    }

    private void consumeData(@NotNull ItemStack itemStack, @NotNull Consumer<NBTTagCompound> consumer) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        consumer.accept(itemCompound);
    }

    private <T> T supplyData(@NotNull ItemStack itemStack, @NotNull Function<NBTTagCompound, T> function) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        return function.apply(itemCompound);
    }


    public ItemBuilder setDisplayName(String displayName) {
        consumeMeta(itemMeta -> itemMeta.setDisplayName(displayName.replace("&", "§")));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        consumeMeta(itemMeta -> itemMeta.setLore(Arrays.stream(lore).map(line -> line.replace("&", "§")).collect(Collectors.toList())));
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder setFlags(ItemFlag... flags) {
        consumeMeta(itemMeta -> itemMeta.addItemFlags(flags));
        return this;
    }

    public String getString(ItemStack stack, String key) {
        return supplyData(stack, nbtTagCompound -> nbtTagCompound.getString(key));
    }

    public long getLong(ItemStack stack, String key) {
        return supplyData(stack, compound -> compound.getLong(key));
    }

    public int getInt(ItemStack stack, String key) {
        return supplyData(stack, compound -> compound.getInt(key));
    }

    public double getDouble(ItemStack stack, String key) {
        return supplyData(stack, nbtTagCompound -> nbtTagCompound.getDouble(key));
    }

    public boolean getBoolean(ItemStack stack, String key) {
        return supplyData(stack, nbtTagCompound -> nbtTagCompound.getBoolean(key));
    }

    public byte getByte(ItemStack stack, String key) {
        return supplyData(stack, nbtTagCompound -> nbtTagCompound.getByte(key));
    }

    public float getFloat(ItemStack stack, String key) {
        return supplyData(stack, nbtTagCompound -> nbtTagCompound.getFloat(key));
    }

    public short getShort(ItemStack stack, String key) {
        return supplyData(stack, nbtTagCompound -> nbtTagCompound.getShort(key));
    }

    public ItemBuilder setLong(ItemStack stack, String key, long value) {
        consumeData(stack, compound -> compound.setLong(key, value));
        return this;
    }

    public ItemBuilder setString(ItemStack stack, String key, String value) {
        consumeData(stack, compound -> compound.setString(key, value));
        return this;
    }

    public ItemBuilder setDouble(ItemStack stack, String key, double value) {
        consumeData(stack, compound -> compound.setDouble(key, value));
        return this;
    }

    public ItemBuilder setInt(ItemStack stack, String key, int value) {
        consumeData(stack, compound -> compound.setInt(key, value));
        return this;
    }

    public ItemBuilder setBoolean(ItemStack stack, String key, boolean value) {
        consumeData(stack, compound -> compound.setBoolean(key, value));
        return this;
    }

    public ItemBuilder setByte(ItemStack stack, String key, byte value) {
        consumeData(stack, compound -> compound.setByte(key, value));
        return this;
    }

    public ItemBuilder setFloat(ItemStack stack, String key, float value) {
        consumeData(stack, compound -> compound.setFloat(key, value));
        return this;
    }

    public ItemBuilder setShort(ItemStack stack, String key, short value) {
        consumeData(stack, compound -> compound.setShort(key, value));
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}