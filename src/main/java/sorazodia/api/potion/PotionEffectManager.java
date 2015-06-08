package sorazodia.api.potion;

import java.util.HashMap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class PotionEffectManager
{
	private static HashMap<String, Potion> potionList = new HashMap<String, Potion>();

	public static void registerPotion(String modID, Potion newPotion)
	{
		String unlocalizedPotionName = modID + ":" + newPotion.getName();

		if (!potionList.containsKey(unlocalizedPotionName))
		{
			potionList.put(unlocalizedPotionName, newPotion);
		} else if (potionList.containsKey(unlocalizedPotionName))
		{
			FMLLog.log(Level.ERROR, "[PotionEffectManager] Somehow, %s is already registered. It was not added.", newPotion.getName());
			FMLLog.log(Level.DEBUG, "[PotionEffectManager] $s's unlocalized name: %s", newPotion.getName(), unlocalizedPotionName);
		} else
		{
			FMLLog.log(Level.ERROR, "[PotionEffectManager] Unexpected Error in Registration");
		}
	}

	public static void applyEffect(EntityLivingBase target, String modID, String potionName, int duration, int powerLevel)
	{
		String unlocalizatedName = modID + ":" + potionName;
		if (potionList.containsKey(unlocalizatedName))
		{
			PotionNBTList list = PotionNBTList.getNBT(target);
			checkEffectOverwrite(list.getNBTList(), unlocalizatedName);
			list.addPotions(unlocalizatedName, duration, powerLevel);
		} else
		{
			FMLLog.log(Level.ERROR, "%s in %s does not exist!", potionName, modID);
		}
	}

	public static void removeEffects(EntityLivingBase target, String unlocalizatedName, boolean blackList)
	{
		PotionNBTList activeEffects = PotionNBTList.getNBT(target);
		NBTTagList effectList = activeEffects.getNBTList();

		for (int x = 0; x < effectList.tagCount(); x++)
		{
			if (blackList == true)
			{
				if (!activeEffects.getNBTString(x,"name").equals(unlocalizatedName))
					effectList.removeTag(x);
			} else
			{
				if (activeEffects.getNBTString(x,"name").equals(unlocalizatedName))
					effectList.removeTag(x);
			}
		}
	}

	public static void removeEffects(EntityLivingBase target, String modID, String potionName, boolean blackList)
	{
		removeEffects(target, modID + ":" + potionName, blackList);
	}

	public static void removeAllEffects(EntityLivingBase target)
	{
		PotionNBTList activeEffects = PotionNBTList.getNBT(target);
		for (int x = 0; x < activeEffects.getNBTList().tagCount(); x++)
			activeEffects.getNBTList().removeTag(x);
	}

	public static void checkEffectOverwrite(NBTTagList list, String potionName)
	{
		for (int x = 0; x < list.tagCount(); x++)
			if (list.getCompoundTagAt(x).getString("name").equals(potionName))
				list.removeTag(x);
	}

	protected static HashMap<String, Potion> getPotionList()
	{
		return potionList;
	}

	protected static Potion getPotion(String key)
	{
		return potionList.get(key);
	}

}
