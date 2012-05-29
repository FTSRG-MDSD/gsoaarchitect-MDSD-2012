package hu.bme.mit.inf.gs.workflow.genius.helpers;

import java.util.HashSet;
import java.util.Set;

public class UserHelper
{
	Set<CategoryAndHit> categories = new HashSet<CategoryAndHit>();
	Set<CreatorAndHit> creators = new HashSet<CreatorAndHit>();
	public Set<CategoryAndHit> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryAndHit> categories) {
		this.categories = categories;
	}
	public Set<CreatorAndHit> getCreators() {
		return creators;
	}
	public void setCreators(Set<CreatorAndHit> creators) {
		this.creators = creators;
	}
	
	public void hitCategory(CategoryBoundaryEntity categorya)
	{
		boolean found = false;
		for (CategoryAndHit cah : this.categories)
		{
			if (cah.getCategory().getCategoryName().equals(categorya.getCategoryName()))
			{
				found = true;
				cah.setHitCount(cah.getHitCount()+1); // +1 hit
				return;
			}
		}
		if (!found)
		{
			CategoryAndHit cahNew = new CategoryAndHit(categorya, 1);
			this.categories.add(cahNew);
		}
	}
	public void hitCreator(String creatorName)
	{
		boolean found = false;
		for (CreatorAndHit cah : this.creators)
		{
			if (cah.getCreatorName().equals(creatorName))
			{
				found = true;
				cah.setHitCount(cah.getHitCount()+1); // +1 hit
				return;
			}
		}
		if (!found)
		{
			CreatorAndHit cahNew = new CreatorAndHit(creatorName, 1);
			this.creators.add(cahNew);
		}
	}
	
	@Override
	public String toString()
	{
		return "categories:\n"+categories.toString() + "\ncreators:\n"+creators.toString();
	}
}
