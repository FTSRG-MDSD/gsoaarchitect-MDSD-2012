// Generated file
// for AppRepository.
// This component is for C#.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.ServiceModel.Description;

namespace AppRepository
{
    class AppRepositoryProgram
    {
        static void Main(string[] args)
        {
            WebServiceHost host = null;
            try
            {
                host = new WebServiceHost(typeof(AppRepositoryComponent),
                    new Uri("http://localhost:8001"));

                // publishing metadata
                ServiceMetadataBehavior smb = host.Description.Behaviors.Find<ServiceMetadataBehavior>();
                if (smb == null)
                    smb = new ServiceMetadataBehavior();
                smb.HttpGetEnabled = true;
                smb.MetadataExporter.PolicyVersion = PolicyVersion.Policy15; // WS-Policy 1.5
                host.Description.Behaviors.Add(smb);
                // Add MEX endpoint
                host.AddServiceEndpoint(
                  ServiceMetadataBehavior.MexContractName,
                  MetadataExchangeBindings.CreateMexHttpBinding(),
                  "mex"
                );
                var se = host.AddServiceEndpoint(typeof(IAppRepositoryComponent), new WebHttpBinding(), "");
                se.Behaviors.Add(new System.ServiceModel.Description.WebHttpBehavior()
                {
                    HelpEnabled = true
                });


                host.Open();

                Logger.Log("Webservice started.", Logger.Severity.Info);
                Console.WriteLine("Webservice AppRepository started on {0}!", host.BaseAddresses.First());
                Console.WriteLine("Press RETURN to exit!");
                Console.ReadLine();
                Logger.Log("Webservice stopped.", Logger.Severity.Info);
                host.Close();
            }
            catch (Exception e)
            {
                host.Abort();
                Logger.Log("Webservice error occured: " + e.Message, Logger.Severity.Error, 998);

                Console.WriteLine("EXCEPTION");
                Console.WriteLine(e.Message);
                Console.WriteLine();
                Console.BackgroundColor = ConsoleColor.Red;
                Console.ForegroundColor = ConsoleColor.Black;
                Console.WriteLine("The web service must be run as an administrator.");
                Console.ReadLine();
            }
        }
    }
}
