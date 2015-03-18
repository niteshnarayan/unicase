/**
 * <copyright> Copyright (c) 2008-2009 Jonas Helming, Maximilian Koegel. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 */
package org.unicase.workspace.test.changeTracking.operations;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.eclipse.emf.emfstore.client.model.util.EMFStoreCommand;
import org.eclipse.emf.emfstore.common.model.util.ModelUtil;
import org.eclipse.emf.emfstore.server.model.versioning.operations.AbstractOperation;
import org.eclipse.emf.emfstore.server.model.versioning.operations.MultiReferenceSetOperation;
import org.eclipse.emf.emfstore.server.model.versioning.operations.OperationsFactory;
import org.junit.Test;
import org.unicase.workspace.test.WorkspaceTest;
import org.unicase.workspace.test.testmodel.TestElement;

/**
 * Tests for multireferenceset operations.
 * 
 * @author wesendon
 */
public class MultiReferenceSetOperationTest extends WorkspaceTest {

	/**
	 * Set reference to filled list.
	 */
	@Test
	public void setValueToFilledTest() {
		new EMFStoreCommand() {
			@Override
			protected void doRun() {
				TestElement element = getTestElement();
				TestElement oldValue = getTestElement();
				TestElement newValue = getTestElement();

				element.getReferences().add(oldValue);
				clearOperations();

				assertTrue(element.getReferences().size() == 1);
				assertTrue(element.getReferences().get(0).equals(oldValue));

				element.getReferences().set(0, newValue);

				assertTrue(element.getReferences().size() == 1);
				assertTrue(element.getReferences().get(0).equals(newValue));

				assertTrue(getProjectSpace().getOperations().size() == 1);
				assertTrue(getProjectSpace().getOperations().get(0) instanceof MultiReferenceSetOperation);
			}
		}.run(false);
	}

	/**
	 * Apply setoperation.
	 */
	@Test
	public void applyValueToFilledTest() {
		new EMFStoreCommand() {
			@Override
			protected void doRun() {
				TestElement testElement = getTestElement();
				TestElement oldValue = getTestElement();
				testElement.getReferences().add(oldValue);
				TestElement newValue = getTestElement();
				assertTrue(testElement.getReferences().size() == 1);
				assertTrue(testElement.getReferences().get(0).equals(oldValue));

				MultiReferenceSetOperation operation = OperationsFactory.eINSTANCE.createMultiReferenceSetOperation();
				operation.setFeatureName("references");
				operation.setIndex(0);
				operation.setNewValue(ModelUtil.getProject(newValue).getModelElementId(newValue));
				operation.setOldValue(ModelUtil.getProject(oldValue).getModelElementId(oldValue));
				operation.setModelElementId(ModelUtil.getProject(testElement).getModelElementId(testElement));

				operation.apply(getProject());

				assertTrue(testElement.getReferences().size() == 1);
				assertTrue(testElement.getReferences().get(0).equals(newValue));
			}
		}.run(false);
	}

	/**
	 * Apply setoperation with wrong index. Note: The set function now operates with mainly the model element ids, the
	 * index is only used for soft conflict detection.
	 */
	@Test
	public void applyValueToFilledWrongIndexTest() {
		new EMFStoreCommand() {
			@Override
			protected void doRun() {
				TestElement testElement = getTestElement();
				TestElement oldValue = getTestElement();
				testElement.getReferences().add(oldValue);
				TestElement newValue = getTestElement();

				assertTrue(testElement.getReferences().size() == 1);
				assertTrue(testElement.getReferences().get(0).equals(oldValue));

				MultiReferenceSetOperation operation = OperationsFactory.eINSTANCE.createMultiReferenceSetOperation();
				operation.setFeatureName("references");
				operation.setIndex(42);
				operation.setNewValue(ModelUtil.getProject(newValue).getModelElementId(newValue));
				operation.setOldValue(ModelUtil.getProject(oldValue).getModelElementId(oldValue));
				operation.setModelElementId(ModelUtil.getProject(testElement).getModelElementId(testElement));

				operation.apply(getProject());

				assertTrue(testElement.getReferences().size() == 1);
				assertTrue(testElement.getReferences().get(0).equals(newValue));
			}
		}.run(false);
	}

	/**
	 * Set value to filled list.
	 */
	@Test
	public void applyValueToMultiFilledTest() {
		new EMFStoreCommand() {
			@Override
			protected void doRun() {
				TestElement testElement = getTestElement();
				TestElement first = getTestElement();
				TestElement second = getTestElement();
				TestElement third = getTestElement();
				TestElement newValue = getTestElement();

				testElement.getReferences().addAll(Arrays.asList(first, second, third));
				assertTrue(testElement.getReferences().size() == 3);

				MultiReferenceSetOperation operation = OperationsFactory.eINSTANCE.createMultiReferenceSetOperation();
				operation.setFeatureName("references");
				operation.setIndex(1);
				operation.setNewValue(ModelUtil.getProject(newValue).getModelElementId(newValue));
				operation.setOldValue(ModelUtil.getProject(second).getModelElementId(second));
				operation.setModelElementId(ModelUtil.getProject(testElement).getModelElementId(testElement));
				operation.apply(getProject());
				assertTrue(testElement.getReferences().size() == 3);
				assertTrue(testElement.getReferences().get(0).equals(first));
				assertTrue(testElement.getReferences().get(1).equals(newValue));
				assertTrue(testElement.getReferences().get(2).equals(third));
			}
		}.run(false);
	}

	/**
	 * Set and reverse operation.
	 */
	@Test
	public void setAndReverseTest() {
		new EMFStoreCommand() {
			@Override
			protected void doRun() {
				TestElement testElement = getTestElement();
				TestElement oldValue = getTestElement();
				testElement.getReferences().add(oldValue);
				TestElement newValue = getTestElement();

				getProjectSpace().getOperations().clear();
				assertTrue(testElement.getReferences().size() == 1);
				assertTrue(testElement.getReferences().get(0).equals(oldValue));

				testElement.getReferences().set(0, newValue);

				assertTrue(testElement.getReferences().size() == 1);
				assertTrue(testElement.getReferences().get(0).equals(newValue));

				AbstractOperation operation = getProjectSpace().getOperations().get(0).reverse();
				operation.apply(getProject());

				assertTrue(testElement.getReferences().size() == 1);
				assertTrue(testElement.getReferences().get(0).equals(oldValue));
			}
		}.run(false);
	}
}