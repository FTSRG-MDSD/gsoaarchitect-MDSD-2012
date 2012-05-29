// Generated file
// for AppRepository component's data helper.
// This component is for C#.

using System.Collections.Generic;
using System.Reflection;
using System;

namespace AppRepository
{
    public static class AppRepositoryDataHelper
    {
        public static IEnumerable<Type> GetKnownTypes(ICustomAttributeProvider provider)
        {
            System.Collections.Generic.List<System.Type> knownTypes =
                new System.Collections.Generic.List<System.Type>();
            knownTypes.Add(typeof(ApplicationBoundaryEntity));
            knownTypes.Add(typeof(ApplicationAccountBoundaryEntity));
            knownTypes.Add(typeof(ApplicationMetadataBoundaryEntity));
            knownTypes.Add(typeof(ApplicationVersionBoundaryEntity));
            knownTypes.Add(typeof(CategoryBoundaryEntity));
            
            return knownTypes;
        }
    }
}
