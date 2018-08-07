package com.dds.journal.tools;

import com.dds.journal.constants.Constants;
import com.dds.journal.domain.User;

public class AppTool {
	
	public static String buildUserProfileImagePath(User user, String fileName) {
		return Constants.USER_IMAGES_DIRECTORY + user.getUsername() + "/" + fileName;
	}

}
