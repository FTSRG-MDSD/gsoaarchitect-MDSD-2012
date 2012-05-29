/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package soamodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import soamodel.ServiceOrientedArchitecture;
import soamodel.SoamodelFactory;
import soamodel.SoamodelPackage;

/**
 * This is the item provider adapter for a {@link soamodel.ServiceOrientedArchitecture} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ServiceOrientedArchitectureItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceOrientedArchitectureItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ServiceOrientedArchitecture_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ServiceOrientedArchitecture_name_feature", "_UI_ServiceOrientedArchitecture_type"),
				 SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__COMPONENTS);
			childrenFeatures.add(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__DATA_TYPES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ServiceOrientedArchitecture.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ServiceOrientedArchitecture"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ServiceOrientedArchitecture)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ServiceOrientedArchitecture_type") :
			getString("_UI_ServiceOrientedArchitecture_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ServiceOrientedArchitecture.class)) {
			case SoamodelPackage.SERVICE_ORIENTED_ARCHITECTURE__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case SoamodelPackage.SERVICE_ORIENTED_ARCHITECTURE__COMPONENTS:
			case SoamodelPackage.SERVICE_ORIENTED_ARCHITECTURE__DATA_TYPES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__COMPONENTS,
				 SoamodelFactory.eINSTANCE.createServiceComponent()));

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__COMPONENTS,
				 SoamodelFactory.eINSTANCE.createCSharpComponent()));

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__COMPONENTS,
				 SoamodelFactory.eINSTANCE.createJEEComponent()));

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__COMPONENTS,
				 SoamodelFactory.eINSTANCE.createOSGiComponent()));

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__DATA_TYPES,
				 SoamodelFactory.eINSTANCE.createEntity()));

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__DATA_TYPES,
				 SoamodelFactory.eINSTANCE.createBuiltInDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__DATA_TYPES,
				 SoamodelFactory.eINSTANCE.createCollectionType()));

		newChildDescriptors.add
			(createChildParameter
				(SoamodelPackage.Literals.SERVICE_ORIENTED_ARCHITECTURE__DATA_TYPES,
				 SoamodelFactory.eINSTANCE.createEnum()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return SoaModelEditPlugin.INSTANCE;
	}

}
