package sorazodia.api.potion;

import net.minecraft.entity.EntityLivingBase;

/**
 * Creates new potion effects
 * 
 * @author SoraZodia
 */
public abstract class Potion
{

	/**
	 * Gets the name of the effect.
	 * 
	 * @return the name of the potion effect
	 */
	public abstract String getName();

	/**
	 * Will cause its target, namely an entity, to be affected as dictated by
	 * the method body. Ex: effect() can have algorithms that causes the target
	 * to become an potato.
	 */
	public abstract void effect(EntityLivingBase target, int powerLevel);

	/**
	 * Will the effect will removed via milk. It will return true by default
	 */
	public boolean milkRemove()
	{
		return true;
	}

}
