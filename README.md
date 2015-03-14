# gsoaarchitect
Automatically exported from code.google.com/p/gsoaarchitect

## About the GSoaArchitect project

This is a pilot project for creating a codegenerator for Service Oriented Architecture. The project has been developed by Team GS at the [Fault Tolerant Systems Research Group](http://inf.mit.bme.hu/en) of [Budapest University of Technology and Economics](http://www.bme.hu).

The purpose of the project was to create a code generator tool, which can generate (JavaEE and .NET) source code from models (created with our Domain Specific Language for Service Oriented Architectures). Our tool generates also the ORM for each component (with Entity Framework and JPA). 

The generated source code contains only method declarations, therefore the implementation of the webservices shall be done manually, but our experience showed, that this way the implementation needs much less time, as a lot of boilerplate code is already generated. The generation of the datamodel and the Object-Relational Mapping makes the data persistence transparent and easy to use.

We have also created validation tools for our modelling environment to check whether the input models are correct. For this purpose we have used OCL and EMF-IncQuery technologies.

A short introduction and an example can be seen in [this document](http://www.scribd.com/horanyi_gergo/d/97137399-Document#fullscreen). Our repository contains not only the code generator tool, but we also created a fully implemented example project, where we created an Application Store generated from a model. For more information please watch [our presentation](http://www.slideshare.net/darvasd/creating-an-appstore-using-model-driven-software-development). The contents of the repository is detailed on [this page](RepoContents.md).


## Future plans

The main goal of our project was to test the previously named technologies, and create a proof-of-concept tool for Service Oriented Architecture code generation. For this reason our short-term plans only cover bugfixes and minor improvements. On the other hand,  our long-term plans include other upgrades too:
  * As the EMF-!IncQuery technology is a highly developing technology, in the future it wil give us more and more possibilities. We intend to create new validation constraints with the use of the new features of the future !IncQuery releases
  * Create a more user-friendly environment for the modelling and code generation workflow
  * Create an update site
