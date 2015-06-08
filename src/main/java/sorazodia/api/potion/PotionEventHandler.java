package sorazodia.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionEventHandler
{

	@SubscribeEvent
	public void entityUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase target = event.entityLiving;
		if (PotionNBTList.getNBT(target) != null)
		{
			PotionNBTList list = PotionNBTList.getNBT(target);
			NBTTagList potions = list.getNBTList();
			if (target.ticksExisted % 20 == 0
					&& list.getNBTList().tagCount() > 0)
			{
				for (int x = 0; x < potions.tagCount(); x++)
				{
					int powerLevel = list.getNBTInt(x, "powerLevel");
					float duration = list.getNBTFloat(x, "duration");
					String potionName = list.getNBTString(x, "name");

					applyEffect(potionName, target, powerLevel);
					if (duration != -1)
						list.setNBTFloat(x, "duration", duration - 1);
					if (duration <= 0 && duration != -1)
						potions.removeTag(x);
				}
			}
		}
	}

	@SubscribeEvent()
	public void milkDrink(PlayerUseItemEvent.Finish itemUse)
	{
		PotionNBTList activeEffects = PotionNBTList.getNBT(itemUse.entityPlayer);

		if (itemUse.item.getItem() == Items.milk_bucket)
		{
			int effectActive = activeEffects.getNBTList().tagCount();
			for (int x = 0; x < effectActive; x++)
			{
				String effectName = activeEffects.getCompoundFromList(x).getString("name");
				System.out.println(effectName);
				if (PotionEffectManager.getPotion(effectName).milkRemove() == true)
					PotionEffectManager.removeEffects(itemUse.entityPlayer, effectName, false);
			}
		}

	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void entityCreate(EntityConstructing create)
	{

		if (create.entity instanceof EntityLivingBase
				&& PotionNBTList.getNBT((EntityLivingBase) create.entity) == null)
		{
			PotionNBTList.register((EntityLivingBase) create.entity);
		}

	}

	private void applyEffect(String potionName, EntityLivingBase target, int powerLevel)
	{
		PotionEffectManager.getPotionList().get(potionName).effect(target, powerLevel);
	}

}
