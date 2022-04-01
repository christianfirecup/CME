package net.mcreator.christiansmenchaments.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;

import net.minecraft.world.World;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.christiansmenchaments.enchantment.XpreloadEnchantment;
import net.mcreator.christiansmenchaments.ChristiansMEnchamentsModVariables;
import net.mcreator.christiansmenchaments.ChristiansMEnchamentsMod;

import java.util.Map;
import java.util.HashMap;

public class XPReloadenchantsProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerXPChange(PlayerXpEvent.XpChange event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				int amount = event.getAmount();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("amount", amount);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency entity for procedure XPReloadenchants!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((EnchantmentHelper.getEnchantmentLevel(XpreloadEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) != 0)) {
			if (Math.random() >= 0.5) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.ARROW);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
				{
					double _setval = ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0);
					entity.getCapability(ChristiansMEnchamentsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.xpofplayer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
