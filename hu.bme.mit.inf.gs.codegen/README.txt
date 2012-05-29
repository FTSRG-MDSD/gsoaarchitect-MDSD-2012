Xpand based code generator for Petri nets

Input: EMF Petri metamodel (src/metamodel/petrinet.ecore)
	   Example instance model (src/PetriNet.xmi). Dynamically created EMF instance from the metamodel.

Output: pnml representation of the input model
	   
How to use:
 * Works only with Xtend 1.0 or later 
 * Tested with Eclipse 3.6 M6!
 * Simply run As 'MWE Workflow' on the generato.mwe file (located in src/workflow/generator.mwe)

Tricky parts:
 * Xtend contains the MWE runtime but for better tooling support install MWE SDK.
 * XMLBeautifier works only if file extension is xml or xmi (not with pnml)
 * Type resolve in the mwe file does not work correctly. It seems that it only requires the element and its parent EPackage and nothing more. in any other combination the type is unresorvable (even with fully qualified name!)
 * Documentation is outdated (up to 2010.03.31). Always check that references to classes (usually in mwe files) are present in their package (location)!