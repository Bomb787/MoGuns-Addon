package deco.init;

import deco.PizzaGummiRat;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
	
	//Workbench
	public static final Block WORKBENCH = new Block(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE));
	
	//Brass
	public static final Block BRASS_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
	
	//Silver
	public static final Block SILVER_ORE = new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE));
	public static final Block SILVER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
	public static final Block DEEPSLATE_SILVER_ORE = new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE));
	public static final Block RAW_SILVER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK));
	
	//Zinc
	public static final Block ZINC_ORE = new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE));
	public static final Block ZINC_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
	public static final Block DEEPSLATE_ZINC_ORE = new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE));
	public static final Block RAW_ZINC_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK));
	
	public static void init() {
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "workbench"), WORKBENCH);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "workbench"), 
				new BlockItem(WORKBENCH, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "brass_block"), BRASS_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "brass_block"), 
				new BlockItem(BRASS_BLOCK, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "silver_ore"), SILVER_ORE);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "silver_ore"), 
				new BlockItem(SILVER_ORE, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "silver_block"), SILVER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "silver_block"), 
				new BlockItem(SILVER_BLOCK, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "deepslate_silver_ore"), DEEPSLATE_SILVER_ORE);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "deepslate_silver_ore"), 
				new BlockItem(DEEPSLATE_SILVER_ORE, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "raw_silver_block"), RAW_SILVER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "raw_silver_block"), 
				new BlockItem(RAW_SILVER_BLOCK, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "zinc_ore"), ZINC_ORE);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "zinc_ore"), 
				new BlockItem(ZINC_ORE, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "zinc_block"), ZINC_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "zinc_block"), 
				new BlockItem(ZINC_BLOCK, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "deepslate_zinc_ore"), DEEPSLATE_ZINC_ORE);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "deepslate_zinc_ore"), 
				new BlockItem(DEEPSLATE_ZINC_ORE, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
		Registry.register(Registry.BLOCK, new Identifier(PizzaGummiRat.MOD_ID, "raw_zinc_block"), RAW_ZINC_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, "raw_zinc_block"), 
				new BlockItem(RAW_ZINC_BLOCK, new FabricItemSettings().group(PizzaGummiRat.Deco)));
		
	}	

}
