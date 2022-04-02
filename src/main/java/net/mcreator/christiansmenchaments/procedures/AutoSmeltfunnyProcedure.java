package net.mcreator.christiansmenchaments.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.block.Blocks;

import net.mcreator.christiansmenchaments.enchantment.AutoSmeltEnchantment;
import net.mcreator.christiansmenchaments.ChristiansMEnchamentsMod;

import java.util.Map;
import java.util.HashMap;

public class AutoSmeltfunnyProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onBlockBreak(BlockEvent.BreakEvent event) {
			Entity entity = event.getPlayer();
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("xpAmount", event.getExpToDrop());
			dependencies.put("x", event.getPos().getX());
			dependencies.put("y", event.getPos().getY());
			dependencies.put("z", event.getPos().getZ());
			dependencies.put("px", entity.getPosX());
			dependencies.put("py", entity.getPosY());
			dependencies.put("pz", entity.getPosZ());
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("blockstate", event.getState());
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency world for procedure AutoSmeltfunny!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency x for procedure AutoSmeltfunny!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency y for procedure AutoSmeltfunny!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency z for procedure AutoSmeltfunny!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ChristiansMEnchamentsMod.LOGGER.warn("Failed to load dependency entity for procedure AutoSmeltfunny!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
				&& ((world instanceof World)
						? ((World) world).getRecipeManager()
								.getRecipe(IRecipeType.SMELTING,
										new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
										(World) world)
								.isPresent()
						: false)) {
			if (dependencies.get("event") != null) {
				Object _obj = dependencies.get("event");
				if (_obj instanceof Event) {
					Event _evt = (Event) _obj;
					if (_evt.isCancelable())
						_evt.setCanceled(true);
				}
			}
			if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) < 0
					&& Math.random() <= 0.4) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			} else if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 1
					&& Math.random() >= 0.4) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			} else if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 2
					&& Math.random() <= 0.3) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			} else if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 2
					&& Math.random() == Math.max(0.4, 0.75)) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			} else if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 3
					&& Math.random() <= 0.3) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			} else if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 3
					&& Math.random() == Math.max(0.31, 0.75)) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			} else if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 3
					&& Math.random() == Math.max(0.75, 0.85)) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			} else if (EnchantmentHelper.getEnchantmentLevel(AutoSmeltEnchantment.enchantment,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0
					&& ((world instanceof World)
							? ((World) world).getRecipeManager()
									.getRecipe(IRecipeType.SMELTING,
											new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
											(World) world)
									.isPresent()
							: false)
					&& EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 3
					&& Math.random() == Math.max(0.85, 1)) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z,
							((world instanceof World && ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									((World) world)).isPresent())
											? ((World) world).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													(World) world).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			}
		}
	}
}
