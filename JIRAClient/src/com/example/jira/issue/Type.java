package com.example.jira.issue;

public enum Type {

	ATLASSIAN_TASK(1), BUG(2), CUSTOMER_PROBLEM(3), DEPENDENCY(4), DEV_SPEED(5), DEVELOPMENT_TASK(
			6), DOCUMENTATION(7), ENHANCEMENT(8), EPIC(9), FEATURE(10), FEEDBACK(
			11), FUG(12), IMPROVEMENT(13), INFRASTRUCTURE(14), INQUIRY(15), LAUREL_MARKETING_TASK(
			16), MARKETING_REQUEST(17), NEW_FEATURE(18), NON_PROJECT_TASK(19), OEM_TASK(
			20), PM_DECISION(21), REQUEST(22), RETROSPECTIVE(23), RISK(24), STORY(
			25), SUGGESTION(26), SUPPORT_REQUEST(27), TASK(28), TECHNICAL_STORY(
			29), TEST_CASE(30), TESTING_TASK(31), THIRD_PARTY_ISSUE(32), UX(33), UX_STORY(
			34);

	private long mId;

	private Type(long id) {
		mId = id;
	}

	public Type getById(long id) {
		for (Type type : values())
			if (type.mId == id)
				return type;
		return null;
	}

}
