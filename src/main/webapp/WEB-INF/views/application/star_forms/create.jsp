<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="errorMessage"
	class="bs-callout bs-callout-danger <c:if test="${empty errorMessage}">hidden</c:if>">
	<c:out value="${errorMessage}" />
</div>

<style>
.combostyle {
    background-color: #FFFFFF;
    color: #36465D;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    width: 250px;
    height: 55px;
    border-radius: 3px 3px 3px 3px;
    border: 1px solid #AAA;
}
</style>

<script type="text/javascript">
function addCombo() {
	var textb = document.getElementById("txtCombo");
	var combo = document.getElementById("discovererCombo");
	
	var option = document.createElement("option");
	option.text = textb.value;
	option.value = textb.value;
	try {
		combo.add(option, null); //Standard 
	}catch(error) {
		combo.add(option); // IE only
	}
	textb.value = "";
}
</script>

<c:url value="/admin/star_update" var="formAction" />
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
			<label for="galacticLongitude" class="col-sm-2 control-label">Galactic longitude</label>
			<div class="col-sm-5">
				<form:input cssClass="form-control" path="galacticLongitude" />
			</div>
		</div>

		<div class="form-group">
			<label for="galacticLatitude" class="col-sm-2 control-label">Galactic latitude</label>
			<div class="col-sm-5">
				<form:input cssClass="form-control" path="galacticLatitude" />
			</div>
		</div>
		
		<!-- Star class -->
		<div class="form-group">
			<label class="col-sm-2 control-label">Class</label>
			<div class="col-xs-5 selectContainer" >
				<form:select path="starClass" cssClass="combostyle">
					<form:option value="NONE" label="Select class" />
					<form:options items="${colorList}" />
				</form:select>
			</div>
		</div>
		
		<!-- Star discoverer -->
		<div class="form-group">
			<label class="col-sm-2 control-label">Discoverer</label>
			<div class="col-xs-5 selectContainer">
				<form:select path="discoverer" cssClass="combostyle" id="discovererCombo">
					<form:option value="NONE" label="Select discoverer" />
					<form:options items="${discoverers}" />
				</form:select>
				
				<!-- NOTE_DYN:ax: dynamically add new discoverer to combo, when we update this form, we check, exists or not discoverer, and create it or not. -->	
				<br>
				<br>
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Add new discoverer name to combo..." id="txtCombo">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="addCombo()">Add</button>
					</span>
				</div>
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
					        	galacticLongitude: {
					        		validators: {
					                    notEmpty: {
					                        message: 'Galactic longitude is required and cannot be empty'
					                    },
					                    stringLength: {
							                min: 1,
							                max: 10,
							                message: 'Galactic longitude should not be longer then 10 characters'
							            }
					                }
					        	},
					        	galacticLatitude: {
					        		validators: {
					                    notEmpty: {
					                        message: 'Galactic latitude is required and cannot be empty'
					                    },
						        		stringLength: {
							                min: 0,
							                max: 10,
							                message: 'Galactic latitude should not be longer then 10 characters'
							            }
					        		}
					            },
					        }
						});
	});
</script>
