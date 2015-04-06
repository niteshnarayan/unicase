/**
 * <copyright> Copyright (c) 2009-2012 Chair of Applied Software Engineering, Technische Universit�t M�nchen (TUM).
 * All rights reserved. This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 */
package org.unicase.ui.unicasecommon.common;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.emfstore.client.observer.ESPostCreationObserver;
import org.eclipse.emf.emfstore.internal.server.exceptions.AccessControlException;
import org.unicase.model.UnicaseModelElement;
import org.unicase.model.organization.User;
import org.unicase.ui.unicasecommon.common.util.OrgUnitHelper;

/**
 * Change listener, that sets the creation date and, if available, the creator
 * attribute on UNICASE model elements.
 * 
 * @author emueller
 */
public class UnicasePostModelElementCreationListener implements
		ESPostCreationObserver {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.emfstore.client.model.observers.PostCreationObserver#onCreation(org.eclipse.emf.ecore.EObject)
	 */
	public void onCreation(EObject modelElement) {
		if (modelElement instanceof UnicaseModelElement) {
			UnicaseModelElement unicaseModelElement = (UnicaseModelElement) modelElement;

			// if model element already has a creation date, leave it as is
			if (unicaseModelElement.getCreationDate() != null) {
				return;
			}

			unicaseModelElement.setCreationDate(new Date());
			User user;

			try {
				user = OrgUnitHelper.getCurrentUser(modelElement);
				if (user != null) {
					unicaseModelElement.setCreator(user.getName());
				}
			} catch (AccessControlException e) {
				// do nothing
			}
		}
	}
}