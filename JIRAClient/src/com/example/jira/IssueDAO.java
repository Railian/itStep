package com.example.jira;

import java.util.ArrayList;
import java.util.Set;

public interface IssueDAO {

	/**
	 * 
	 * @param title
	 *            of issue
	 * @param sgid
	 *            - system generated ID
	 * @param authorFk
	 *            - the foreign key of author
	 * @param typeFk
	 *            - the foreign key of priority type
	 * @param statusFk
	 *            - the foreign key of issue status
	 * @param assignedFk
	 *            - the foreign key of user who was assigned
	 * @param details
	 *            - short description of issue
	 * @return auto generated id of row in database
	 */
	public long addIssue(String title, String sgid, long authorFk, long typeFk,
			long statusFk, long assignedFk, long milestonesFk, String details);

	public ArrayList<Issue> getIssueByFilters(long authorFk, long typeFk,
			long statusFk, long assignedFk, long milestonesFk);

	public Set<Long> getAvalableAuthors(long typeFk, long statusFk,
			long assignedFk, long milestonesFk);

}
