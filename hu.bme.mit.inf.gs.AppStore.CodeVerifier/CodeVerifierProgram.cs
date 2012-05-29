// Generated file
// for CodeVerifier.
// This component is for C#.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.ServiceModel.Description;

namespace CodeVerifier
{
    class CodeVerifierProgram
    {
        static void Main(string[] args)
        {
        	WebServiceHost host = null;
            try
            {
                host = new WebServiceHost(typeof(CodeVerifierComponent),
                    new Uri("http://localhost:8004"));
                
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
                var se = host.AddServiceEndpoint(typeof(ICodeVerifierComponent), new WebHttpBinding(), "");
                    se.Behaviors.Add(new System.ServiceModel.Description.WebHttpBehavior()
                    {
                        HelpEnabled = true
                    });


                    host.Open();
                    Console.WriteLine("Webservice CodeVerifier started on {0}!", host.BaseAddresses.First());
                Console.WriteLine("Press RETURN to exit!");
                Console.ReadLine();
                host.Close();
            }
            catch (Exception e)
            {
            	host.Abort();
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
