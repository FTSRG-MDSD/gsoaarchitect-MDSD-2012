// Generated implementation file
// for CodeVerifier component.
// This component is for C#.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace CodeVerifier
{
    public class CodeVerifierComponent : ICodeVerifierComponent 
    {
        private Random random = new Random();

	     ///<summary>
	     /// 
	     ///</summary>
         public int VerifyCode(ApplicationVersionBoundaryEntity applicationVersion)
         {
             if (applicationVersion == null)
             {
                 return AcceptanceResultEnum.REJECTED;
             }
             if (string.IsNullOrWhiteSpace(applicationVersion.versionString))
             {
                 return AcceptanceResultEnum.REJECTED;
             }
             
             // Miracle occurs here

             if (random.NextDouble() > 0.7)
             {
                 return AcceptanceResultEnum.ACCEPTED;
             }
             else
             {
                 return AcceptanceResultEnum.REJECTED;
             }
         }

	     ///<summary>
	     /// 
	     ///</summary>
         public int VerifyMetadata(ApplicationMetadataBoundaryEntity applicationMetadata)
         {
             if (applicationMetadata == null)
             {
                 return AcceptanceResultEnum.REJECTED;
             }

             if (string.IsNullOrWhiteSpace(applicationMetadata.name))
             {
                 return AcceptanceResultEnum.REJECTED;
             }

             if (applicationMetadata.name[0] == 'i')
             {
                 // Hidden joke, but also useful for testing.
                 return AcceptanceResultEnum.ACCEPTED;
             }

             if (applicationMetadata.name.Contains("virus"))
             {
                 return AcceptanceResultEnum.REJECTED;
             }

             if (random.NextDouble() > 0.7)
             {
                 return AcceptanceResultEnum.ACCEPTED;
             }
             else
             {
                 return AcceptanceResultEnum.REJECTED;
             }
         }
  
    }
}
