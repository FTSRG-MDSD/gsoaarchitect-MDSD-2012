// Client data definition of AppRepository for CodeVerifier

namespace CodeVerifier
{
    using System.Runtime.Serialization;
    
    
    
   
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="ApplicationBoundaryEntity", Namespace="http://schemas.datacontract.org/2004/07/Server")]
    public partial class ApplicationBoundaryEntity : object, System.Runtime.Serialization.IExtensibleDataObject
    {       
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData { get; set; }

		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int applicationID { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public ApplicationVersionBoundaryEntity lastAcceptedVersion { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public ApplicationVersionBoundaryEntity lastCommitedVersion { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public ApplicationMetadataBoundaryEntity lastAcceptedMetadata { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public ApplicationMetadataBoundaryEntity lastCommitedMetadata { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string creatorName { get; set; }
		
    }
    
    
    
   
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="ApplicationAccountBoundaryEntity", Namespace="http://schemas.datacontract.org/2004/07/Server")]
    public partial class ApplicationAccountBoundaryEntity : object, System.Runtime.Serialization.IExtensibleDataObject
    {       
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData { get; set; }

		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string accountUserName { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Collections.Generic.List<ApplicationBoundaryEntity> boughtApplications { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int applicationAccountID { get; set; }
		
    }
    
    
    
   
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="ApplicationMetadataBoundaryEntity", Namespace="http://schemas.datacontract.org/2004/07/Server")]
    public partial class ApplicationMetadataBoundaryEntity : object, System.Runtime.Serialization.IExtensibleDataObject
    {       
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData { get; set; }

		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int applicationMetadataID { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.DateTime timestamp { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string name { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public double price { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string description { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Collections.Generic.List<CategoryBoundaryEntity> categories { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int acceptanceResult { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int ageRestriction { get; set; }
		
    }
    
    
    
   
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="ApplicationVersionBoundaryEntity", Namespace="http://schemas.datacontract.org/2004/07/Server")]
    public partial class ApplicationVersionBoundaryEntity : object, System.Runtime.Serialization.IExtensibleDataObject
    {       
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData { get; set; }

		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int applicationVersionID { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.DateTime timestamp { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string versionString { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int acceptanceResult { get; set; }
		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Collections.Generic.List<string> filePaths { get; set; }
		
    }
    
    
    
   
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="CategoryBoundaryEntity", Namespace="http://schemas.datacontract.org/2004/07/Server")]
    public partial class CategoryBoundaryEntity : object, System.Runtime.Serialization.IExtensibleDataObject
    {       
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData { get; set; }

		
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string categoryName { get; set; }
		
    }
    
     
}

