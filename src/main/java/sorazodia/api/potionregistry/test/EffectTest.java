package sorazodia.api.potionregistry.test;

import net.minecraft.entity.EntityLivingBase;
import sorazodia.api.potion.Potion;

public class EffectTest extends Potion
{

	private int count = 0;
	
	@Override
	public String getName()
	{
		return "PotionTest";
	}

	@Override
	public void effect(EntityLivingBase target, int powerLevel)
	{
		System.out.println("Hi " + count++);
		target.heal(1.0F);
	}

}
