using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel.Web;
using System.Net;

namespace AppRepository
{
    public class WebException : WebFaultException<string>
    {
        public WebException(string errorMessage) : base(errorMessage, HttpStatusCode.BadRequest) 
        {
            base.Source = "AppRepository";
        }

        public WebException(string errorMessage, HttpStatusCode statusCode) : base(errorMessage, statusCode)
        {
            base.Source = "AppRepository";
        }
    }
}
