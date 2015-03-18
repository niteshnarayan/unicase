package scrm.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import scrm.diagram.edit.policies.ScrmBaseItemSemanticEditPolicy;
import scrm.knowledge.Mathematical_GeophysicalModel;
import scrm.knowledge.NumericalMethod;

/**
 * @generated
 */
public class Mathematical_GeophysicalModelUsedInNumericalMethodsReorientCommand
		extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject referenceOwner;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public Mathematical_GeophysicalModelUsedInNumericalMethodsReorientCommand(
			ReorientReferenceRelationshipRequest request) {
		super(request.getLabel(), null, request);
		reorientDirection = request.getDirection();
		referenceOwner = request.getReferenceOwner();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == referenceOwner instanceof Mathematical_GeophysicalModel) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof NumericalMethod && newEnd instanceof Mathematical_GeophysicalModel)) {
			return false;
		}
		return ScrmBaseItemSemanticEditPolicy
				.getLinkConstraints()
				.canExistMathematical_GeophysicalModelUsedInNumericalMethods_4065(
						getNewSource(), getOldTarget());
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof NumericalMethod && newEnd instanceof NumericalMethod)) {
			return false;
		}
		return ScrmBaseItemSemanticEditPolicy
				.getLinkConstraints()
				.canExistMathematical_GeophysicalModelUsedInNumericalMethods_4065(
						getOldSource(), getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getOldSource().getUsedInNumericalMethods().remove(getOldTarget());
		getNewSource().getUsedInNumericalMethods().add(getOldTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getOldSource().getUsedInNumericalMethods().remove(getOldTarget());
		getOldSource().getUsedInNumericalMethods().add(getNewTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected Mathematical_GeophysicalModel getOldSource() {
		return (Mathematical_GeophysicalModel) referenceOwner;
	}

	/**
	 * @generated
	 */
	protected Mathematical_GeophysicalModel getNewSource() {
		return (Mathematical_GeophysicalModel) newEnd;
	}

	/**
	 * @generated
	 */
	protected NumericalMethod getOldTarget() {
		return (NumericalMethod) oldEnd;
	}

	/**
	 * @generated
	 */
	protected NumericalMethod getNewTarget() {
		return (NumericalMethod) newEnd;
	}
}