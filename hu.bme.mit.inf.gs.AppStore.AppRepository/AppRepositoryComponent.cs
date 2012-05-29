// Generated implementation file
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
    public class AppRepositoryComponent : IAppRepositoryComponent
    {
        ///<summary>
        /// Lists all applications.
        ///</summary>
        public System.Collections.Generic.List<ApplicationBoundaryEntity> ListApplications()
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                var apps = (from a in context.ApplicationEntitySet
                            where a.lastAcceptedMetadata != null
                            select a);

                List<ApplicationBoundaryEntity> ret = new List<ApplicationBoundaryEntity>();
                foreach (var app in apps)
                {
                    ret.Add(ApplicationBoundaryEntity.CreateFromEFObject(app));
                }
                return ret;
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public System.Collections.Generic.List<ApplicationBoundaryEntity> ListApplicationsByCategory(string categoryName)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                var apps = (from a in context.ApplicationEntitySet
                            where a.lastCommitedMetadata != null && a.lastCommitedMetadata.categories != null &&
                            a.lastCommitedMetadata.categories.Where(x => x.categoryName == categoryName).Count() > 0
                            select a);
                List<ApplicationBoundaryEntity> ret = new List<ApplicationBoundaryEntity>();
                foreach (var app in apps)
                {
                    ret.Add(ApplicationBoundaryEntity.CreateFromEFObject(app));
                }
                return ret;
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public ApplicationBoundaryEntity GetApplication(int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity app = (from a in context.ApplicationEntitySet
                                         where a.applicationID == applicationID
                                         select a).FirstOrDefault();

                if (app == null)
                {
                    Logger.Log(string.Format("Not existing application: id={0}", applicationID),
                        Logger.Severity.Warning, 101);
                    throw new WebException("Not existing application.");
                }

                ApplicationBoundaryEntity ret = ApplicationBoundaryEntity.CreateFromEFObject(app);
                return ret;
            }
        }

        ///<summary>
        /// Creates a new application for the given creator and returns the ID of the new application.
        ///</summary>
        public int CreateApplication(string creator)
        {
            int appid = -1;
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity app = new ApplicationEntity()
                {
                    creatorName = creator
                };
                context.ApplicationEntitySet.AddObject(app);
                context.SafeSaveChanges();
                appid = app.applicationID;
            }
            return appid;
        }

        ///<summary>
        /// 
        ///</summary>
        public void AddApplicationVersion(ApplicationVersionBoundaryEntity version, int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity app = (from a in context.ApplicationEntitySet
                                         where a.applicationID == applicationID
                                         select a).FirstOrDefault();
                app.lastCommitedVersion = version.CreateEFObject();
                context.SafeSaveChanges();
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public void AddApplicationMetadata(ApplicationMetadataBoundaryEntity metadata, int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity app = (from a in context.ApplicationEntitySet
                                         where a.applicationID == applicationID
                                         select a).FirstOrDefault();
                app.lastCommitedMetadata = metadata.CreateEFObject();

                if (metadata.categories != null)
                {
                    foreach (var cat in metadata.categories)
                    {
                        CategoryEntity category = context.CategoryEntitySet.Where(x => x.categoryName == cat.categoryName).FirstOrDefault();
                        if (category == null)
                        {
                            category = new CategoryEntity();
                            category.categoryName = cat.categoryName;
                            context.CategoryEntitySet.AddObject(category);
                        }
                        app.lastCommitedMetadata.categories.Add(category);
                    }
                }
                app.lastCommitedMetadata.timestamp = DateTime.Now;

                context.SafeSaveChanges();
            }
        }

        ///<summary>
        /// Gets the last commited application version.
        ///</summary>
        public ApplicationVersionBoundaryEntity GetApplicationVersion(int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity app = (from a in context.ApplicationEntitySet
                                         where a.applicationID == applicationID
                                         select a).FirstOrDefault();
                if (app == null)
                {
                    Logger.Log(string.Format("Not existing application: id={0}", applicationID),
                        Logger.Severity.Warning, 102);
                    throw new WebException("Not existing application.");
                }
                return ApplicationVersionBoundaryEntity.CreateFromEFObject(app.lastCommitedVersion);
            }
        }

        ///<summary>
        /// Gets the last commited metadata version.
        ///</summary>
        public ApplicationMetadataBoundaryEntity GetApplicationMetadata(int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity app = (from a in context.ApplicationEntitySet
                                         where a.applicationID == applicationID
                                         select a).FirstOrDefault();
                if (app == null)
                {
                    Logger.Log(string.Format("Not existing application: id={0}", applicationID),
                        Logger.Severity.Warning, 103);
                    throw new WebException("Not existing application.");
                }
                return ApplicationMetadataBoundaryEntity.CreateFromEFObject(app.lastCommitedMetadata);
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public void SetMetadataAcceptance(int metadataID, int acceptance)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationMetadataEntity metadata = (
                    from m in context.ApplicationMetadataEntitySet
                    where m.applicationMetadataID == metadataID
                    select m).FirstOrDefault();
                if (metadata == null)
                {
                    Logger.Log(string.Format("Not existing metadata: id={0}", metadataID),
                        Logger.Severity.Warning, 104);
                    throw new WebException("Not existing metadata.");
                }

                metadata.acceptanceResult = acceptance;

                if (acceptance == AcceptanceResultEnum.ACCEPTED)
                {
                    ApplicationEntity app = (from a in context.ApplicationEntitySet
                                             where a.lastCommitedMetadata.applicationMetadataID == metadataID
                                             select a).FirstOrDefault();
                    if (app == null)
                    {
                        //nincs egy apphoz sem társítva commited metadataként
                        Logger.Log(string.Format("The given metadata ID (id={0}) is not an ID of a commited metadata.", metadataID),
                             Logger.Severity.Warning, 105);
                        throw new WebException("The given metadata ID is not an ID of a commited metadata.");
                    }

                    app.lastAcceptedMetadata = metadata;
                }
                context.SafeSaveChanges();
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public void SetVersionAcceptance(int versionID, int acceptance)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationVersionEntity version = (
                    from v in context.ApplicationVersionEntitySet
                    where v.applicationVersionID == versionID
                    select v).FirstOrDefault();
                if (version == null)
                {
                    Logger.Log(string.Format("Not existing version: id={0}", versionID),
                        Logger.Severity.Warning, 106);
                    throw new WebException("Not existing version.");
                }

                version.acceptanceResult = acceptance;

                if (acceptance == AcceptanceResultEnum.ACCEPTED)
                {
                    ApplicationEntity app = (from a in context.ApplicationEntitySet
                                             where a.lastCommitedVersion.applicationVersionID == versionID
                                             select a).FirstOrDefault();
                    if (app == null)
                    {
                        //nincs egy apphoz sem társítva commited metadataként
                        return;
                    }

                    app.lastAcceptedVersion = version;
                    context.SafeSaveChanges();
                }
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public void DeleteApplication(int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity app = (from a in context.ApplicationEntitySet
                                         where a.applicationID == applicationID
                                         select a).FirstOrDefault();
                if (app == null)
                {
                    Logger.Log(string.Format("Not existing application: id={0}", applicationID),
                        Logger.Severity.Warning, 113);
                    throw new WebException("Not existing application.");
                }
                context.ApplicationEntitySet.DeleteObject(app);
                context.SafeSaveChanges();
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public int CreateApplicationAccount(string userLoginName)
        {
            int accountId = -1;
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationAccountEntity existingAcc = context.ApplicationAccountEntitySet
                    .Where(x => x.accountUserName == userLoginName).FirstOrDefault();
                if (existingAcc != null)
                {
                    return existingAcc.applicationAccountID;
                }

                ApplicationAccountEntity account = new ApplicationAccountEntity()
                {
                    accountUserName = userLoginName
                };
                context.ApplicationAccountEntitySet.AddObject(account);
                context.SafeSaveChanges();
                accountId = account.applicationAccountID;
            }
            return accountId;
        }

        ///<summary>
        /// 
        ///</summary>
        public void DeleteApplicationAccount(string userLoginName)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationAccountEntity account = (from acc in context.ApplicationAccountEntitySet
                                                    where acc.accountUserName == userLoginName
                                                    select acc).FirstOrDefault();
                if (account == null)
                {
                    Logger.Log(string.Format("Not existing account: username={0}", userLoginName),
                        Logger.Severity.Warning, 114);
                    throw new WebException("Not existing account.");
                }
                context.ApplicationAccountEntitySet.DeleteObject(account);
                context.SafeSaveChanges();
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public ApplicationAccountBoundaryEntity GetApplicationAccount(string userLoginName)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationAccountEntity account = (from acc in context.ApplicationAccountEntitySet
                                                    where acc.accountUserName == userLoginName
                                                    select acc).FirstOrDefault();
                if (account == null)
                {
                    Logger.Log(string.Format("Not existing account: username={0}", userLoginName),
                        Logger.Severity.Warning, 115);
                    throw new WebException("Not existing account.");
                }
                ApplicationAccountBoundaryEntity ret = ApplicationAccountBoundaryEntity.CreateFromEFObject(account);
                return ret;
            }
        }

        public int GetApplicationPurchaseCount(int applicationID)
        {
            int ret = 0;
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationEntity application =
                    (from app in context.ApplicationEntitySet
                     where app.applicationID == applicationID
                     select app).FirstOrDefault();
                if (application == null)
                {
                    Logger.Log(string.Format("Not existing application: id={0}", applicationID),
                        Logger.Severity.Warning, 108);
                    throw new WebException("Not existing application.");
                }
                var accounts = (from acc in context.ApplicationAccountEntitySet
                             select acc);
                foreach (ApplicationAccountEntity account in accounts)
                {
                    foreach (var app in account.boughtApplications)
                    {
                        if (app.applicationID == application.applicationID)
                            ret++;
                    }
                }
            }
            return ret;
        }

        ///<summary>
        /// 
        ///</summary>
        public void BuyApplication(string userLoginName, int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationAccountEntity account =
                    (from acc in context.ApplicationAccountEntitySet
                     where acc.accountUserName == userLoginName
                     select acc).FirstOrDefault();
                if (account == null)
                {
                    Logger.Log(string.Format("Not existing account: username={0}", userLoginName),
                        Logger.Severity.Warning, 107);
                    throw new WebException("Not existing account.");
                }

                ApplicationEntity application =
                    (from app in context.ApplicationEntitySet
                     where app.applicationID == applicationID
                     select app).FirstOrDefault();
                if (application == null)
                {
                    Logger.Log(string.Format("Not existing application: id={0}", applicationID),
                        Logger.Severity.Warning, 108);
                    throw new WebException("Not existing application.");
                }

                if (account.boughtApplications.Where(x => x.applicationID == application.applicationID).Count() > 0)
                {
                    Logger.Log(string.Format("Already bought application: id={0}", applicationID),
                        Logger.Severity.Warning, 109);
                    throw new WebException("This application is already bought.", System.Net.HttpStatusCode.Forbidden);
                }
                account.boughtApplications.Add(application);
                context.SafeSaveChanges();
            }
        }

        ///<summary>
        /// 
        ///</summary>
        public void UnbuyApplication(string userLoginName, int applicationID)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationAccountEntity account =
                        (from acc in context.ApplicationAccountEntitySet
                         where acc.accountUserName == userLoginName
                         select acc).FirstOrDefault();
                if (account == null)
                {
                    Logger.Log(string.Format("Not existing account: username={0}", userLoginName),
                        Logger.Severity.Warning, 110);
                    throw new WebException("Not existing account.");
                }

                ApplicationEntity application =
                        (from app in context.ApplicationEntitySet
                         where app.applicationID == applicationID
                         select app).FirstOrDefault();
                if (application == null)
                {
                    Logger.Log(string.Format("Not existing application: id={0}", applicationID),
                        Logger.Severity.Warning, 111);
                    throw new WebException("Not existing application.");
                }

                account.boughtApplications.Remove(application);
                context.SafeSaveChanges();
            }
        }

        public System.Collections.Generic.List<ApplicationBoundaryEntity> GetBoughtApplications(string userLoginName)
        {
            using (AppRepositoryContainer context = new AppRepositoryContainer())
            {
                ApplicationAccountEntity account =
                            (from acc in context.ApplicationAccountEntitySet
                             where acc.accountUserName == userLoginName
                             select acc).FirstOrDefault();
                if (account == null)
                {
                    Logger.Log(string.Format("Not existing account: username={0}", userLoginName),
                        Logger.Severity.Warning, 112);
                    throw new WebException("Not existing account.");
                }

                List<ApplicationBoundaryEntity> ret = new List<ApplicationBoundaryEntity>();
                foreach (var app in account.boughtApplications)
                {
                    ret.Add(ApplicationBoundaryEntity.CreateFromEFObject(app));
                }
                return ret;
            }
        }

        public void Error()
        {
            Logger.Log(string.Format("Error test invoked."),
                        Logger.Severity.Info);
            throw new WebException("This is an error message for testing purposes.");
        }
    }
}
