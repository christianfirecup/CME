
package net.mcreator.christiansmenchaments.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.christiansmenchaments.ChristiansMEnchamentsModElements;

@ChristiansMEnchamentsModElements.ModElement.Tag
public class XpreloadEnchantment extends ChristiansMEnchamentsModElements.ModElement {
	@ObjectHolder("christians_m_enchaments:xpreload")
	public static final Enchantment enchantment = null;

	public XpreloadEnchantment(ChristiansMEnchamentsModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("xpreload"));
	}

	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.COMMON, EnchantmentType.CROSSBOW, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 1;
		}

		@Override
		protected boolean canApplyTogether(Enchantment ench) {
			if (ench == Enchantments.POWER)
				return true;
			if (ench == Enchantments.PUNCH)
				return true;
			if (ench == Enchantments.FLAME)
				return true;
			if (ench == Enchantments.UNBREAKING)
				return true;
			if (ench == Enchantments.VANISHING_CURSE)
				return true;
			if (ench == Enchantments.PIERCING)
				return true;
			if (ench == Enchantments.QUICK_CHARGE)
				return true;
			return false;
		}

		@Override
		public boolean canApplyAtEnchantingTable(ItemStack stack) {
			if (stack.getItem() == Items.CROSSBOW)
				return true;
			return false;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}

		@Override
		public boolean canGenerateInLoot() {
			return true;
		}

		@Override
		public boolean canVillagerTrade() {
			return true;
		}
	}
}
