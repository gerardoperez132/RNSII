<div class="pagination">
	<s:if test="totalPages > 1">
		<ul>
			<s:if test="hasPrevious">
				<li class="previous"><a href="?page=<s:property value="%{page-1}"/>">« <s:text name="previous"/></a></li>
			</s:if>
			<s:else>
				<li class="previous-off">« <s:text name="previous"/></li>
			</s:else>
			<s:iterator value="pagination">
				<s:set name="currentPage" value="page"/>
				<s:set name="paginationPage"><s:property/></s:set>
				<s:if test="#currentPage==#paginationPage">
					<li class="active"><s:property/></li>
				</s:if>
				<s:else>
					<li><a href="?page=<s:property/>"><s:property/></a></li>
				</s:else>
			</s:iterator>
			<s:if test="hasNext">
				<li class="next"><a href="?page=<s:property value="%{page+1}"/>"><s:text name="next"/> »</a></li>
			</s:if>
			<s:else>
				<li class="next-off"><s:text name="next"/> »</li>
			</s:else>
		</ul>
	</s:if>
</div>
