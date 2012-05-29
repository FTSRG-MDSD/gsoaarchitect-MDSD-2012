package hu.bme.mit.inf.gs.workflow.genius.helpers;

public class CategoryAndHit
{
	private CategoryBoundaryEntity category;
	private Integer hitCount;
	
	public CategoryBoundaryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryBoundaryEntity category) {
		this.category = category;
	}
	public Integer getHitCount() {
		return hitCount;
	}
	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}
	
	private CategoryAndHit(){}
	
	public CategoryAndHit(CategoryBoundaryEntity categ, Integer hitco)
	{
		this.category = categ;
		this.hitCount = hitco;
	}
	
	@Override
	public String toString()
	{
		return category+"("+hitCount+")";
	}
}
