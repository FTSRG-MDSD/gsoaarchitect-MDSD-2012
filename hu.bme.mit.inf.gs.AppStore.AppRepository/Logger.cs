using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;

namespace AppRepository
{
    public static class Logger
    {
        private const string LogName = "AppStore";
        private const string Source = "AppRepository";
        private const ConsoleColor ConsoleColor = System.ConsoleColor.DarkGreen;

        public enum Severity
        {
            Info = 1,
            Warning = 2,
            Error = 3
        }
        
        public static Severity MinimumSeverity
        {
            get;
            set;
        }

        public static bool ConsoleOutputEnabled
        {
            get;
            set;
        }

        static Logger()
        {
            MinimumSeverity = Severity.Info;
            ConsoleOutputEnabled = true;
        }

        public static void Log(string message, Severity severity, int eventID = 0)
        {
            try
            {
                if (severity < MinimumSeverity)
                {
                    return;
                }

                if (!EventLog.SourceExists(Source))
                {
                    EventLog.CreateEventSource(Source, LogName);
                }

                EventLogEntryType type;
                switch (severity)
                {
                    case Severity.Info:
                        type = EventLogEntryType.Information;
                        break;
                    case Severity.Warning:
                        type = EventLogEntryType.Warning;
                        break;
                    case Severity.Error:
                        type = EventLogEntryType.Error;
                        break;
                    default:
                        throw new NotImplementedException();
                }

                EventLog.WriteEntry(Source, message, type, eventID);

                if (ConsoleOutputEnabled || severity == Severity.Error)
                {
                    LogToConsole(message, severity, eventID);
                }
            }
            catch (Exception ex)
            {
                //best effort
                LogToConsole("Logging error occured. " + ex.Message, Severity.Error, -1);
            }    
        }

        private static void LogToConsole(string message, Severity severity, int eventID)
        {
            ConsoleColor oldcolor = Console.ForegroundColor;
            Console.ForegroundColor = ConsoleColor;

            Console.WriteLine("[{3:HH:mm:ss}] {0,-8} {1,3} {2}", 
                severity.ToString().ToUpper(),
                eventID,
                message,
                DateTime.Now);

            Console.ForegroundColor = oldcolor;
        }
    }
}
