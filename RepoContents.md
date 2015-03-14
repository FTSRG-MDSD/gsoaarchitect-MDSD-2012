Our repository contains both Java and .NET projects. There are projects for the modelling and code generator tool, and we have also shared a detailed, fully implemendted example project for an AppStore we have introduced in our [presentation](http://www.slideshare.net/darvasd/creating-an-appstore-using-model-driven-software-development). <font color='red'>We marked the projects of the example with red color</font>. These projects are not necessary for using the code generator.

Java projects:

| <font color='red'>hu.bme.mit.inf.gs.AppStore.CreditManger.working</font> | This project contains the fully implemented, working CreditManager component of the AppStore. |
|:-------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------|
| <font color='red'>hu.bme.mit.inf.gs.AppStore.UserManger.working</font> | This project contains the fully implemented, working UserManager component of the AppStore. |
| hu.bme.mit.inf.gs.codegen | This contains the sources for the code generator |
| hu.bme.mit.inf.gs.dsl | This contains our DSL, and a complex instance of the DSL metamodel |
| hu.bme.mit.inf.gs.dsl.edit | EMF generated edit project for DSL |
| hu.bme.mit.inf.gs.dsl.editor | EMF generated tree-editor project for DSL |
| hu.bme.mit.inf.gs.dsl.validator | EMF-IncQuery constraints for the DSL validation |
| <font color='red'>hu.bme.mit.inf.gs.sampledatauploader</font> | This project upload sample data to the component's database for testing the AppStore Genius|
| <font color='red'>hu.bme.mit.inf.gs.uml.activitymodel<font> <table><thead><th> Activity model for designing the workflows of the AppStore (Papyrus) </th></thead><tbody>
<tr><td> <font color='red'>hu.bme.mit.inf.gs.uml.activitymodel2<font> </td><td> Another activity model for designing the workflows of the example AppStore (Papyrus) </td></tr>
<tr><td> <font color='red'>hu.bme.mit.inf.gs.uml.usecase</font> </td><td> Usecase model for designing our AppStore architecture (Papyrus) </td></tr></tbody></table>





.NET projects:<br>
<br>
<table><thead><th> <font color='red'>hu.bme.mit.inf.gs.AppStore.AppRepository</font> </th><th> This project contains the fully implemented, working AppRepository component of AppStore. </th></thead><tbody>
<tr><td> <font color='red'>hu.bme.mit.inf.gs.AppStore.CodeVerifier</font> </td><td> This project contains the fully implemented, working CodeVerifier component. </td></tr>