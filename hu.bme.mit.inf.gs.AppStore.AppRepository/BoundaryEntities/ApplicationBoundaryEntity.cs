
// This entity is for C#.
// Used in web service methods, not for persisting data.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AppRepository
{
    public class ApplicationBoundaryEntity
    {
        public int applicationID
        {
            get;
            set;
        }
        public ApplicationVersionBoundaryEntity lastAcceptedVersion
        {
            get;
            set;
        }
        public ApplicationVersionBoundaryEntity lastCommitedVersion
        {
            get;
            set;
        }
        public ApplicationMetadataBoundaryEntity lastAcceptedMetadata
        {
            get;
            set;
        }
        public ApplicationMetadataBoundaryEntity lastCommitedMetadata
        {
            get;
            set;
        }
        public string creatorName
        {
            get;
            set;
        }


        public static ApplicationBoundaryEntity CreateFromEFObject(ApplicationEntity ef)
        {
            if (ef == null)
            {
                return null;
            }
            ApplicationBoundaryEntity inst = new ApplicationBoundaryEntity();
            inst.applicationID = ef.applicationID;
            inst.creatorName = ef.creatorName;
            inst.lastAcceptedMetadata =
                ApplicationMetadataBoundaryEntity.CreateFromEFObject(ef.lastAcceptedMetadata);
            inst.lastCommitedMetadata =
                ApplicationMetadataBoundaryEntity.CreateFromEFObject(ef.lastCommitedMetadata);
            inst.lastAcceptedVersion =
                ApplicationVersionBoundaryEntity.CreateFromEFObject(ef.lastAcceptedVersion);
            inst.lastCommitedVersion =
                ApplicationVersionBoundaryEntity.CreateFromEFObject(ef.lastCommitedVersion);

            return inst;
        }

        public ApplicationEntity CreateEFObject()
        {
            ApplicationEntity inst = new ApplicationEntity();
            inst.applicationID = this.applicationID;
            inst.creatorName = this.creatorName;
            return inst;
        }
    }
}
