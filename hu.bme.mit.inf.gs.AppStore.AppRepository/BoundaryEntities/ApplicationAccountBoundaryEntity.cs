
// This entity is for C#.
// Used in web service methods, not for persisting data.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AppRepository
{
    public class ApplicationAccountBoundaryEntity
    {
        public string accountUserName
        {
            get;
            set;
        }
        public System.Collections.Generic.List<ApplicationBoundaryEntity> boughtApplications
        {
            get;
            set;
        }
        public int applicationAccountID
        {
            get;
            set;
        }


        public static ApplicationAccountBoundaryEntity CreateFromEFObject(ApplicationAccountEntity ef)
        {
            if (ef == null)
            {
                return null;
            }
            ApplicationAccountBoundaryEntity inst = new ApplicationAccountBoundaryEntity();
            inst.accountUserName = ef.accountUserName;
            inst.applicationAccountID = ef.applicationAccountID;
            return inst;
        }

        public ApplicationAccountEntity CreateEFObject()
        {
            ApplicationAccountEntity inst = new ApplicationAccountEntity();
            inst.accountUserName = this.accountUserName;
            inst.applicationAccountID = this.applicationAccountID;
            return inst;
        }
    }
}
