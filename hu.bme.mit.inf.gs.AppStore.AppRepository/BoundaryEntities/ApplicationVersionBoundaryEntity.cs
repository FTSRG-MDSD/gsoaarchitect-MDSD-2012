
// This entity is for C#.
// Used in web service methods, not for persisting data.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AppRepository
{
    public class ApplicationVersionBoundaryEntity
    {
        public int applicationVersionID
        {
            get;
            set;
        }
        public System.DateTime timestamp
        {
            get;
            set;
        }
        public string versionString
        {
            get;
            set;
        }
        public int acceptanceResult
        {
            get;
            set;
        }
        public System.Collections.Generic.List<string> filePaths
        {
            get;
            set;
        }


        public static ApplicationVersionBoundaryEntity CreateFromEFObject(ApplicationVersionEntity ef)
        {
            if (ef == null)
            {
                return null;
            }
            ApplicationVersionBoundaryEntity inst = new ApplicationVersionBoundaryEntity();
            inst.applicationVersionID = ef.applicationVersionID;
            inst.timestamp = ef.timestamp.Value;
            inst.versionString = ef.versionString;
            inst.acceptanceResult = ef.acceptanceResult.Value;
            inst.filePaths = new List<string>();
            foreach (var f in ef.filePaths)
            {
                inst.filePaths.Add(f.filePaths);
            }
            return inst;
        }

        public ApplicationVersionEntity CreateEFObject()
        {
            ApplicationVersionEntity inst = new ApplicationVersionEntity();
            inst.applicationVersionID = this.applicationVersionID;
            inst.timestamp = this.timestamp;
            inst.versionString = this.versionString;
            inst.acceptanceResult = this.acceptanceResult;
            return inst;
        }
    }
}
