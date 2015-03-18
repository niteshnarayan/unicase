package scrm.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

import scrm.diagram.edit.policies.Mathematical_GeophysicalModelDependenciesItemSemanticEditPolicy;

/**
 * @generated
 */
public class Mathematical_GeophysicalModelDependenciesEditPart extends
		ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4066;

	/**
	 * @generated
	 */
	public Mathematical_GeophysicalModelDependenciesEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new Mathematical_GeophysicalModelDependenciesItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof WrappingLabel6EditPart) {
			((WrappingLabel6EditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureMathematical_GeophysicalModel_DependenciesLabel());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, index);
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof WrappingLabel6EditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	protected Connection createConnectionFigure() {
		return new Mathematical_GeophysicalModel_DependenciesFigure();
	}

	/**
	 * @generated
	 */
	public Mathematical_GeophysicalModel_DependenciesFigure getPrimaryShape() {
		return (Mathematical_GeophysicalModel_DependenciesFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class Mathematical_GeophysicalModel_DependenciesFigure extends
			PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureMathematical_GeophysicalModel_DependenciesLabel;

		/**
		 * @generated
		 */
		public Mathematical_GeophysicalModel_DependenciesFigure() {

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureMathematical_GeophysicalModel_DependenciesLabel = new WrappingLabel();
			fFigureMathematical_GeophysicalModel_DependenciesLabel
					.setText("depends");

			this.add(fFigureMathematical_GeophysicalModel_DependenciesLabel);

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureMathematical_GeophysicalModel_DependenciesLabel() {
			return fFigureMathematical_GeophysicalModel_DependenciesLabel;
		}

	}

}