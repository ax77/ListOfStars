<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="errorMessage"
	class="bs-callout bs-callout-danger <c:if test="${empty errorMessage}">hidden</c:if>">
	<c:out value="${errorMessage}" />
</div>

<c:url value="/admin/star_discoverer_update" var="formAction" />
<form:form method="POST" modelAttribute="notes" action="${formAction}"
	cssClass="form-horizontal top50" role="form" id="notesForm">

	<fieldset>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name</label>
			<div class="col-sm-5">
				<form:input cssClass="form-control" path="name" />
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-5">
				<label class="checkbox-inline"> </label>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-5">
				<button type="submit" class="btn btn-default">Create</button>
			</div>
		</div>
		

	</fieldset>
</form:form>

<script>
	// form validation. displays error message
	$(function() {
		$('#notesForm')
				.bootstrapValidator(
						{
							feedbackIcons : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							live : 'enabled',
							message : 'This value is not valid',
							submitButtons : 'button[type="submit"]',
							submitHandler : null,
							trigger : null,
					        fields: {
					        	name: {
					                validators: {
					                    notEmpty: {
					                        message: 'Name is required and cannot be empty'
					                    },
					                    stringLength: {
							                min: 1,
							                max: 255,
							                message: 'Name should not be longer then 255 characters'
							            }
					                }
					        	},
					        }
						});
	});
</script>
