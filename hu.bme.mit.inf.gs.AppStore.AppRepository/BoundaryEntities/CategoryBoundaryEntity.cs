
// This entity is for C#.
// Used in web service methods, not for persisting data.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AppRepository
{
    public class CategoryBoundaryEntity
    {
        public string categoryName
        {
            get;
            set;
        }


        public static CategoryBoundaryEntity CreateFromEFObject(CategoryEntity ef)
        {
            if (ef == null)
            {
                return null;
            }
            CategoryBoundaryEntity inst = new CategoryBoundaryEntity();
            inst.categoryName = ef.categoryName;
            return inst;
        }

        public CategoryEntity CreateEFObject()
        {
            CategoryEntity inst = new CategoryEntity();
            inst.categoryName = this.categoryName;
            return inst;
        }
    }
}
