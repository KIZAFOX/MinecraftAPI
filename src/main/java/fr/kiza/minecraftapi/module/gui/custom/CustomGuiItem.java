package fr.kiza.minecraftapi.module.gui.custom;

import fr.kiza.minecraftapi.module.gui.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class CustomGuiItem extends GuiItem {

    private final Material material;
    private final String name;
    private final List<String> description;

    private final double dropRate;

    public CustomGuiItem(final Material material, final String name, final List<String> description, final double dropRate) {
        this.material = material;
        this.name = name;
        this.description = description;
        this.dropRate = dropRate;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    public double getDropRate() {
        return dropRate;
    }

    @Override
    public void onClick(Player player, ClickType clickType) { }
}
