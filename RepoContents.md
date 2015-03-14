#Contents of the repository

Our repository contains both Java and .NET projects. There are projects for the modelling and code generator tool, and we have also shared a detailed, fully implemendted example project for an AppStore we have introduced in our [presentation](http://www.slideshare.net/darvasd/creating-an-appstore-using-model-driven-software-development). *We marked the projects of the example with italic font*. These projects are not necessary for using the code generator.

## Java projects

Project | Description
------- | -----------
*hu.bme.mit.inf.gs.AppStore.CreditManger.working* | This project contains the fully implemented, working CreditManager component of the AppStore. 
 *hu.bme.mit.inf.gs.AppStore.UserManger.working* | This project contains the fully implemented, working UserManager component of the AppStore. 
 hu.bme.mit.inf.gs.codegen | This contains the sources for the code generator 
 hu.bme.mit.inf.gs.dsl | This contains our DSL, and a complex instance of the DSL metamodel 
 hu.bme.mit.inf.gs.dsl.edit | EMF generated edit project for DSL 
 hu.bme.mit.inf.gs.dsl.editor | EMF generated tree-editor project for DSL 
 hu.bme.mit.inf.gs.dsl.validator | EMF-IncQuery constraints for the DSL validation
 *hu.bme.mit.inf.gs.sampledatauploader* | This project upload sample data to the component's database for testing the AppStore Genius
 *hu.bme.mit.inf.gs.uml.activitymodel* | Activity model for designing the workflows of the AppStore (Papyrus) 
 *hu.bme.mit.inf.gs.uml.activitymodel2* | Another activity model for designing the workflows of the example !AppStore (Papyrus) 
 *hu.bme.mit.inf.gs.uml.usecase* | Usecase model for designing our AppStore architecture (Papyrus) 


##.NET projects

Project | Description
------- | -----------
 *hu.bme.mit.inf.gs.AppStore.AppRepository* | This project contains the fully implemented, working AppRepository component of AppStore. 
*hu.bme.mit.inf.gs.AppStore.CodeVerifier* | This project contains the fully implemented, working CodeVerifier component. 
