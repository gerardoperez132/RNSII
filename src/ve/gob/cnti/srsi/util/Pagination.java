/* This file is part of SRSI.
 * 
 * SRSI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SRSI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SRSI. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.srsi.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilidad para generar paginación de contenidos.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class Pagination {
	private int page = 1;
	private int totalPages = 1;
	private boolean hasPrevious;
	private boolean hasNext;
	private List<?> content = new ArrayList<Object>();
	private List<Integer> pagination = new ArrayList<Integer>();

	/**
	 * Constructor del objeto de paginación.
	 * 
	 * @param list
	 *            Lista completa de los contenidos a ser paginados.
	 * @param limit
	 *            Límite de contenidos mostrados por página.
	 * @param page
	 *            Página actual.
	 */
	public Pagination(List<?> list, int limit, int page) {
		super();
		this.page = page;
		List<Object> tmp = new ArrayList<Object>();
		if (list.size() > 0) {
			double expression = list.size() / (double) limit;
			this.totalPages = (int) (expression % 1 != 0 ? expression + 1
					: expression);
			if (this.page <= 0 || this.page > this.totalPages)
				this.page = 1;
			if (this.totalPages < 1)
				this.totalPages = 1;
			for (int i = ((this.page - 1) * limit); i < this.page * limit; i++) {
				if (i >= list.size())
					break;
				tmp.add(list.get(i));
			}
			if (this.page > 1 && this.page < this.totalPages) {
				this.hasPrevious = true;
				this.hasNext = true;
			} else if (this.page == this.totalPages) {
				this.hasPrevious = true;
				this.hasNext = false;
			} else {
				this.hasPrevious = false;
				this.hasNext = true;
			}
			for (int j = 0; j < this.totalPages; j++)
				this.pagination.add(j + 1);
		}
		this.content = tmp;
	}

	@Override
	public String toString() {
		return "Pagination [page=" + page + ", totalPages=" + totalPages
				+ ", hasPrevious=" + hasPrevious + ", hasNext=" + hasNext
				+ ", content=" + content + ", pagination=" + pagination + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<?> getContent() {
		return content;
	}

	public void setContent(List<?> content) {
		this.content = content;
	}

	public List<Integer> getPagination() {
		return pagination;
	}

	public void setPagination(List<Integer> pagination) {
		this.pagination = pagination;
	}
}