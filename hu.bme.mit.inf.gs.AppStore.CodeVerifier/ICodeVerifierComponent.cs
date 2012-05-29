// Generated interface file
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
    [ServiceContract]
    [ServiceKnownType("GetKnownTypes", typeof(CodeVerifierDataHelper))]
    interface ICodeVerifierComponent
    {
	     ///<summary>
	     /// 
	     ///</summary>
	     [WebInvoke(Method = "POST", UriTemplate = "code")]
         [OperationContract(Name = "VerifyCode")]
         int VerifyCode(ApplicationVersionBoundaryEntity applicationVersion);
           
	     ///<summary>
	     /// 
	     ///</summary>
	     [WebInvoke(Method = "POST", UriTemplate = "metadata")]
         [OperationContract(Name = "VerifyMetadata")]
         int VerifyMetadata(ApplicationMetadataBoundaryEntity applicationMetadata);
           
  
    }
}
