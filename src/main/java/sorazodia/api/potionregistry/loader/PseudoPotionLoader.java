package sorazodia.api.potionregistry.loader;

import net.minecraftforge.common.MinecraftForge;
import sorazodia.api.potion.PotionEventHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = PseudoPotionLoader.NAME, modid = PseudoPotionLoader.MODID, version = PseudoPotionLoader.VERSION)
public class PseudoPotionLoader
{

	public static final String MODID = "pseudoPotion";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "Pseudo Potion API";

	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent)
	{
		FMLLog.info("[PseudoPotion] Initializating");
		MinecraftForge.EVENT_BUS.register(new PotionEventHandler());
		FMLLog.info("[PseudoPotion] Initializated");
	}

}