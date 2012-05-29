using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AppRepository
{
    public static class Extensions
    {
        public static void SafeSaveChanges(this AppRepositoryContainer context)
        {
            try
            {
                context.SaveChanges();
            }
            catch (Exception e)
            {
                Logger.Log(string.Format("Persistence error: {0}\n{1}", e.Message, e.StackTrace),
                        Logger.Severity.Error, 201);
                throw new WebException("Error occured during the persisting phase.", System.Net.HttpStatusCode.InternalServerError);
            }
        }
    }
}
