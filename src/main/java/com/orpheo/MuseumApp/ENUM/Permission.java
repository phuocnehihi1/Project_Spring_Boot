package com.orpheo.MuseumApp.ENUM;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
		ADMIN_READ("admin:read"),
	    ADMIN_UPDATE("admin:update"),
	    ADMIN_CREATE("admin:create"),
	    ADMIN_DELETE("admin:delete"),
	    MANAGER_READ("management:read"),
	    MANAGER_UPDATE("management:update"),
	    MANAGER_CREATE("management:create"),
	    MANAGER_DELETE("management:delete")

	    ;
		
		private Permission(String permission) {
			this.permission = permission;
		}

		@Getter
	    private final String permission;

		public String getPermission() {
			return permission;
		}
		
		//Perssion > Role > User >>>> UserDetails
}
