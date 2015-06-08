package sorazodia.api.potionregistry.test;

import sorazodia.api.potion.PotionEffectManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

@Mod(modid = PotionTest.MODID, version = PotionTest.VERSION, name = PotionTest.NAME)
public class PotionTest
{
	public static final String MODID = "PotionTest";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "PotionTest";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent)
	{
		PotionEffectManager.registerPotion(MODID, new EffectTest());
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onTick(PlayerTickEvent tick)
	{
		if(tick.player.isSneaking())
		{
			PotionEffectManager.applyEffect(tick.player, MODID, "PotionTest", 40, 1);
		}
//		else
//		  PotionEffectManager.removeEffects(tick.player, MODID, "PotionTest", false);
	}
	
}
