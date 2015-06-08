package sorazodia.api.potionregistry.test;

import sorazodia.api.potion.Potion;
import net.minecraft.entity.EntityLivingBase;

public class RegenrationPotion extends Potion
{

	@Override
	public String getName()
	{
		return "regenerate";
	}

	@Override
	public void effect(EntityLivingBase target, int powerLevel)
	{
		if (target.ticksExisted % 50 == 0)
			target.setHealth(target.getHealth() + 1.0F);
		
		System.out.println("sup");
	}

}
