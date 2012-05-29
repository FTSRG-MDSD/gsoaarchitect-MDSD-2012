
// This entity is for C#.
// Used in web service methods, not for persisting data.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AppRepository
{
    public class ApplicationMetadataBoundaryEntity
    {
        public int applicationMetadataID
        {
            get;
            set;
        }
        public System.DateTime timestamp
        {
            get;
            set;
        }
        public string name
        {
            get;
            set;
        }
        public double price
        {
            get;
            set;
        }
        public string description
        {
            get;
            set;
        }
        public System.Collections.Generic.List<CategoryBoundaryEntity> categories
        {
            get;
            set;
        }
        public int acceptanceResult
        {
            get;
            set;
        }
        public int ageRestriction
        {
            get;
            set;
        }


        public static ApplicationMetadataBoundaryEntity CreateFromEFObject(ApplicationMetadataEntity ef)
        {
            if (ef == null)
            {
                return null;
            }
            ApplicationMetadataBoundaryEntity inst = new ApplicationMetadataBoundaryEntity();
            inst.applicationMetadataID = ef.applicationMetadataID;
            inst.timestamp = ef.timestamp.Value;
            inst.name = ef.name;
            inst.price = ef.price.Value;
            inst.description = ef.description;
            inst.acceptanceResult = ef.acceptanceResult.Value;
            inst.ageRestriction = ef.ageRestriction.Value;
            inst.categories = new List<CategoryBoundaryEntity>();
            foreach (var cat in ef.categories)
            {
                inst.categories.Add(CategoryBoundaryEntity.CreateFromEFObject(cat));
            }

            return inst;
        }

        public ApplicationMetadataEntity CreateEFObject()
        {
            ApplicationMetadataEntity inst = new ApplicationMetadataEntity();
            inst.applicationMetadataID = this.applicationMetadataID;
            inst.timestamp = this.timestamp;
            inst.name = this.name;
            inst.price = this.price;
            inst.description = this.description;
            inst.acceptanceResult = this.acceptanceResult;
            inst.ageRestriction = this.ageRestriction;
            return inst;
        }
    }
}
