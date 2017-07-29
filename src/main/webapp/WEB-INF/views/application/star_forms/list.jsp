<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="infoMessage"
	class="bs-callout bs-callout-info <c:if test="${empty infoMessage}">hidden</c:if>">
	<c:out value="${infoMessage}" />
</div>

<div id="errorMessage"
	class="bs-callout bs-callout-danger <c:if test="${empty errorMessage}">hidden</c:if>">
	<c:out value="${errorMessage}" />
</div>


<a href='<s:url value="/admin/star_create" />' class="btn btn-primary">New</a>
<br><br>

<table class="table table-bordered table-striped table-responsive">
	<thead>
		<tr>
			<th>#</th>
			<th>Name</th>
			
			<!-- Galactic coordinate system -->
			<th>G. lon.</th>
			<th>G. lat.</th>
			
			<th>Star class</th>
			<th>Discoverer</th>
			
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${resultList.pageList}" var="notes"
			varStatus="status">
			<tr>
				
				<!-- Row counts -->
				<td class="col-md-1"><c:out value="${resultList.pageSize * resultList.page + status.count}" /></td>
				
				<!-- Link for edit -->
				<td class="col-md-2">
						<a href='<s:url value="/admin/star_edit/${notes.name}" />'>
						<c:out value="${notes.name}" />
				</a></td>
				
				<td class="col-md-1"><c:out value="${notes.galacticLongitude}" /></td>
				<td class="col-md-1"><c:out value="${notes.galacticLatitude}" /></td>
				<td class="col-md-2"><c:out value="${notes.starClass}" /></td>
				<td class="col-md-2"><c:out value="${notes.discoverer}" /></td>
				

				<!-- Buttons -->
				<td class="col-md-1"><nobr>
						
				<a class="btn btn-primary btn-xs"
					href="/ListOfStars/admin/star_edit/${notes.name}"> <span
					class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				</a>
						
				<a class="btn btn-danger btn-xs"
					onclick="return confirm('Are you sure you want to delete this star?');"
					href="/ListOfStars/admin/star_delete/${notes.name}"> 
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a>
				
	
				</nobr></td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>


<%-- Pagination --%>
<c:if test="${resultList.nrOfElements > 5}">
	<div class="row">
		<div class="col-md-8 col-sm-6 col-xs-6">
			<c:if test="${resultList.pageCount > 1}">
				<ul class="pagination">
					<%-- Previous button --%>
					<li <c:if test="${resultList.firstPage}"> class="disabled"</c:if>>
						<a
						href="
							<c:choose>
								<c:when test="${resultList.firstPage}">#</c:when>
								<c:when test="${! resultList.firstPage}"><s:url value="/admin/starlist?page=${resultList.page-1}" /></c:when>
							</c:choose>
						">&laquo;</a>
					</li>

					<%-- First page and dots --%>
					<c:if test="${resultList.pageCount > 5 && resultList.page > 2}">
						<li><a href='<s:url value="/admin/starlist?page=0" />'>1</a></li>
						<c:if test="${resultList.page != 3 && resultList.pageCount > 6}">
							<li class="disabled"><a href="#">...</a></li>
						</c:if>
					</c:if>

					<%-- Print numbers --%>
					<c:forEach begin="0" end="${resultList.pageCount-1}"
						varStatus="pageNumber">
						<c:if
							test="${
							pageNumber.index == resultList.page ||
							pageNumber.index == resultList.page - 1 ||
							pageNumber.index == resultList.page - 2 ||
							pageNumber.index == resultList.page + 1 ||
							pageNumber.index == resultList.page + 2 ||
							((resultList.page == 0 || resultList.page == 1) && pageNumber.index == resultList.page + 3) ||
							(resultList.page == 0 && pageNumber.index == resultList.page + 4) ||
							(resultList.page >= resultList.pageCount - 3 && pageNumber.index >= resultList.pageCount - 5)
						}">
							<li
								<c:if test="${resultList.page == pageNumber.index}"> class="active"</c:if>>
								<a
								href="<s:url value="/admin/starlist?page=${pageNumber.index}" />">${pageNumber.index+1}</a>
							</li>
						</c:if>
					</c:forEach>

					<%-- Last page and dots --%>
					<c:if
						test="${resultList.page < resultList.pageCount - 3 &&	resultList.pageCount > 5}">
						<c:if
							test="${resultList.page != resultList.pageCount - 4 && resultList.pageCount > 6}">
							<li class="disabled"><a href="#">...</a></li>
						</c:if>
						<li><a
							href='<s:url value="/admin/starlist?page=${resultList.pageCount-1}" />'>${resultList.pageCount}</a></li>
					</c:if>

					<%-- Next button --%>
					<li <c:if test="${resultList.lastPage}"> class="disabled"</c:if>>
						<a
						href="
							<c:choose>
								<c:when test="${resultList.lastPage}">#</c:when>
								<c:when test="${! resultList.lastPage}"><s:url value="/admin/starlist?page=${resultList.page+1}" /></c:when>
							</c:choose>
						">&raquo;</a>
					</li>
				</ul>
			</c:if>
		</div>
		<div class="col-md-4 col-sm-6 col-xs-6 top20">
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-xs-7" for="resultsPerPage">Results
						per page: </label>
					<div class="col-xs-4">
						<select class="form-control" id="resultsPerPage">
							<option <c:if test="${resultList.pageSize == 5}"> selected</c:if>>5</option>
							<option
								<c:if test="${resultList.pageSize == 10}"> selected</c:if>>10</option>
							<option
								<c:if test="${resultList.pageSize == 15}"> selected</c:if>>15</option>
							<option
								<c:if test="${resultList.pageSize == 20}"> selected</c:if>>20</option>
							<option
								<c:if test="${resultList.pageSize == 25}"> selected</c:if>>25</option>
							<option
								<c:if test="${resultList.pageSize == 30}"> selected</c:if>>30</option>
						</select>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script>
		// Change page size after changing selection in #resultsPerPage drop down
		$(function() {
			$('#resultsPerPage')
					.change(
							function() {
								var pageSize = $('#resultsPerPage').val();
								location.href = '<s:url value="/admin/starlist?pageSize=" />'
										+ pageSize;
							});
		});
	</script>
</c:if>
<%-- END Pagination --%>


<span class="label label-primary">Total: ${count}</span>




