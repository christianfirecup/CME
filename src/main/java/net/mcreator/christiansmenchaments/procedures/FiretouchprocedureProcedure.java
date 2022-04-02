package net.mcreator.christiansmenchaments.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.christiansmenchaments.enchantment.FireTouchEnchantment;
import net.mcreator.christiansmenchaments.ChristiansMEnchamentsMod;

import java.util.Map;
import java.util.HashMap;

public class FiretouchprocedureProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityAttacked(LivingAttackEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				Entity imediatesourceentity = event.getSource().getImmediateSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				double amount = event.getAmount();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("amount", amount);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("imediatesourceentity", imediatesourceentity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency entity for procedure Firetouchprocedure!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency sourceentity for procedure Firetouchprocedure!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (entity instanceof PlayerEntity) {
			if (EnchantmentHelper.getEnchantmentLevel(FireTouchEnchantment.enchantment,
					((entity instanceof LivingEntity)
							? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST)
							: ItemStack.EMPTY)) == 0) {
				sourceentity.setFire((int) 10);
			}
			if (EnchantmentHelper.getEnchantmentLevel(FireTouchEnchantment.enchantment,
					((entity instanceof LivingEntity)
							? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST)
							: ItemStack.EMPTY)) == 1) {
				sourceentity.setFire((int) 30);
			}
			if (EnchantmentHelper.getEnchantmentLevel(FireTouchEnchantment.enchantment,
					((entity instanceof LivingEntity)
							? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST)
							: ItemStack.EMPTY)) >= 2) {
				sourceentity.setFire((int) 40);
			}
			if (EnchantmentHelper.getEnchantmentLevel(FireTouchEnchantment.enchantment,
					((entity instanceof LivingEntity)
							? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST)
							: ItemStack.EMPTY)) >= 3) {
				sourceentity.setFire((int) 40);
			}
		}
	}
}
