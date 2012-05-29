// Generated interface file
// for AppRepository component.
// This component is for C#.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace AppRepository
{
    [ServiceContract]
    [ServiceKnownType("GetKnownTypes", typeof(AppRepositoryDataHelper))]
    interface IAppRepositoryComponent
    {
        ///<summary>
        /// Lists all applications.
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "applications")]
        [OperationContract(Name = "ListApplications")]
        System.Collections.Generic.List<ApplicationBoundaryEntity> ListApplications();

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "applications/{categoryName}")]
        [OperationContract(Name = "ListApplicationsByCategory")]
        System.Collections.Generic.List<ApplicationBoundaryEntity> ListApplicationsByCategory(string categoryName);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "application/?id={applicationID}")]
        [OperationContract(Name = "GetApplication")]
        ApplicationBoundaryEntity GetApplication(int applicationID);

        ///<summary>
        /// Creates a new application for the given creator and returns the ID of the new application.
        ///</summary>
        [WebInvoke(Method = "PUT", UriTemplate = "application/{creator}")]
        [OperationContract(Name = "CreateApplication")]
        int CreateApplication(string creator);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "PUT", UriTemplate = "application/version/?id={applicationID}")]
        [OperationContract(Name = "AddApplicationVersion")]
        void AddApplicationVersion(ApplicationVersionBoundaryEntity version, int applicationID);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "PUT", UriTemplate = "application/metadata/?id={applicationID}")]
        [OperationContract(Name = "AddApplicationMetadata")]
        void AddApplicationMetadata(ApplicationMetadataBoundaryEntity metadata, int applicationID);

        ///<summary>
        /// Gets the last commited application version.
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "application/version/?id={applicationID}")]
        [OperationContract(Name = "GetApplicationVersion")]
        ApplicationVersionBoundaryEntity GetApplicationVersion(int applicationID);

        ///<summary>
        /// Gets the last commited metadata version.
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "application/metadata/?id={applicationID}")]
        [OperationContract(Name = "GetMetadataVersion")]
        ApplicationMetadataBoundaryEntity GetApplicationMetadata(int applicationID);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "PUT", UriTemplate = "application/metadata/acceptance/?id={metadataID}&acceptance={acceptance}")]
        [OperationContract(Name = "SetMetadataAcceptance")]
        void SetMetadataAcceptance(int metadataID, int acceptance);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "PUT", UriTemplate = "application/version/acceptance/?id={versionID}&acceptance={acceptance}")]
        [OperationContract(Name = "SetVersionAcceptance")]
        void SetVersionAcceptance(int versionID, int acceptance);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "DELETE", UriTemplate = "application/?id={applicationID}")]
        [OperationContract(Name = "DeleteApplication")]
        void DeleteApplication(int applicationID);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "PUT", UriTemplate = "applicationaccount/{userLoginName}")]
        [OperationContract(Name = "CreateApplicationAccount")]
        int CreateApplicationAccount(string userLoginName);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "DELETE", UriTemplate = "applicationaccount/{userLoginName}")]
        [OperationContract(Name = "DeleteApplicationAccount")]
        void DeleteApplicationAccount(string userLoginName);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "applicationaccount/{userLoginName}")]
        [OperationContract(Name = "GetApplicationAccount")]
        ApplicationAccountBoundaryEntity GetApplicationAccount(string userLoginName);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "applicationsajat/?id={applicationID}")]
        [OperationContract(Name = "GetApplicationPurchaseCount")]
        int GetApplicationPurchaseCount(int applicationID);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "PUT", UriTemplate = "buy/{userLoginName}/?id={applicationID}")]
        [OperationContract(Name = "BuyApplication")]
        void BuyApplication(string userLoginName, int applicationID);

        ///<summary>
        /// 
        ///</summary>
        [WebInvoke(Method = "DELETE", UriTemplate = "buy/{userLoginName}/?id={applicationID}")]
        [OperationContract(Name = "UnbuyApplication")]
        void UnbuyApplication(string userLoginName, int applicationID);

        ///<summary>
        ///
        ///</summary>
        [WebInvoke(Method = "GET", UriTemplate = "applications/bought/{userLoginName}")]
        [OperationContract(Name = "GetBoughtApplications")]
        System.Collections.Generic.List<ApplicationBoundaryEntity> GetBoughtApplications(string userLoginName);

        [WebInvoke(Method = "PUT", UriTemplate = "error")]
        [OperationContract(Name = "Error")]
        void Error();
    }
}
